package indep.vafl.control;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import indep.vafl.datarepo.QuizRepository;
import indep.vafl.entity.Quiz;
import indep.vafl.service.StatisticService;
import indep.vafl.utility.EntityNotFoundException;

@RestController
public class QuizController {
	@Autowired
	QuizRepository quizRepository;

	@Autowired
	StatisticService statService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/quizValid")
	public Page<Quiz> getAllByValidation(Pageable pageable) {
		return quizRepository.findByQuizValidation(true, pageable);
	}

	@Secured ({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/quizAll")
	public Page<Quiz> getAll(Pageable pageable) {
		return quizRepository.findAll(pageable);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/quiz")
	public Quiz createQuiz(@Valid @RequestBody Quiz quiz) {
		quiz.setValidation(false);
		quiz = quizRepository.save(quiz);
		statService.newStatistic(quiz.getId());
		return quiz;
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/quiz/{quizID}")
	public Quiz updateQuiz(@PathVariable(value = "quizID") Integer quizID, @Valid @RequestBody Quiz quizRequest) {
		return quizRepository.findById(quizID).map(quiz -> {
			quiz.setQuizDescription(quizRequest.getQuizDescription());
			quiz.setValidation(quizRequest.getValidation());
			return quizRepository.save(quiz);
		}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + "not found"));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/quiz/{quizID}/validate")
	public Quiz validateQuiz(@PathVariable(value = "quizID") Integer quizID) {
		return quizRepository.findById(quizID).map(quiz -> {
			quiz.setValidation(true);
			return quizRepository.save(quiz);
		}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + "not found"));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/quiz/{quizID}/invalidate")
	public Quiz invalidateQuiz(@PathVariable(value = "quizID") Integer quizID) {
		return quizRepository.findById(quizID).map(quiz -> {
			quiz.setValidation(false);
			return quizRepository.save(quiz);
		}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + "not found"));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/quiz/{quizID}")
	public ResponseEntity<?> deleteQuiz(@PathVariable(value = "quizID") Integer quizID) {
		return quizRepository.findById(quizID).map(quiz -> {
			quizRepository.delete(quiz);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + " not found"));
	}
}

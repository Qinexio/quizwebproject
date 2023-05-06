package indep.vafl.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import indep.vafl.datarepo.QuestionRepository;
import indep.vafl.datarepo.QuizRepository;
import indep.vafl.entity.Question;
import indep.vafl.utility.EntityNotFoundException;

@RestController
public class QuestionController {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	QuizRepository quizRepository;

	@Value("${admin.limit}")
	Integer questionLimit;

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/quiz/{quizID}/questions")
	public Page<Question> getByQuizID(@PathVariable(value = "quizID") Integer quizID,
			@PageableDefault(value = 30) Pageable pageable) {
		return questionRepository.findByQuestionQuizId(quizID, pageable);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/quiz/{quizID}/questionsRAND")
	public Page<Question> getByQuizIDRAND(@PathVariable(value = "quizID") Integer quizID,
			@PageableDefault(value = 30) Pageable pageable) {
		List<Question> toAdd = new ArrayList<>();
		List<Integer> toAddPresent = new ArrayList<>();
		List<Question> allQuestions = questionRepository.findByQuestionQuizId(quizID, pageable).getContent();
		Random rand = new Random();
		int count = 0;

		if (allQuestions.size() <= questionLimit)
			return new PageImpl<Question>(allQuestions, pageable, allQuestions.size());

		while (count < questionLimit) {
			int randomNum = rand.nextInt(allQuestions.size());
			count++;
			if (!toAddPresent.contains(randomNum)) {
				toAdd.add(allQuestions.get(randomNum));
				toAddPresent.add(randomNum);
			}

		}

		return new PageImpl<Question>(toAdd, pageable, toAdd.size());
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@GetMapping("/quiz/{quizID}/question/{questionID}")
	public Optional<Question> getQuestionByID(@PathVariable(value = "quizID") Integer quizID,
			@PathVariable(value = "questionID") Long questionID) {
		return questionRepository.findById(questionID);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/quiz/{quizID}/question")
	public Question createQuestion(@PathVariable(value = "quizID") Integer quizID,
			@Valid @RequestBody Question question) {
		return quizRepository.findById(quizID).map(quiz -> {
			question.setQuestionQuiz(quiz);
			return questionRepository.save(question);
		}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + " not found"));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/quiz/{quizID}/question/{questionID}")
	public Question updateQuestion(@PathVariable(value = "quizID") Integer quizID,
			@PathVariable(value = "questionID") Long questionID, @Valid @RequestBody Question questionRequest) {
		if (!quizRepository.existsById(quizID)) {
			throw new EntityNotFoundException("quizID " + quizID + " not found");
		}

		return questionRepository.findById(questionID).map(question -> {
			question.setQuestionDescription(questionRequest.getQuestionDescription());
			return questionRepository.save(question);
		}).orElseThrow(() -> new EntityNotFoundException("questionID " + questionID + "not found"));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/quiz/{quizID}/question/{questionID}")
	public ResponseEntity<?> deleteQuestion(@PathVariable(value = "quizID") Integer quizID,
			@PathVariable(value = "questionID") Long questionID) {
		if (!quizRepository.existsById(quizID)) {
			throw new EntityNotFoundException("quizID " + quizID + " not found");
		}

		return questionRepository.findById(questionID).map(question -> {
			questionRepository.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new EntityNotFoundException("questionID " + questionID + " not found"));
	}

}

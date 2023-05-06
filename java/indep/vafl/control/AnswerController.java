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

import indep.vafl.datarepo.AnswerRepository;
import indep.vafl.datarepo.QuestionRepository;
import indep.vafl.entity.Answer;
import indep.vafl.utility.EntityNotFoundException;

@RestController
public class AnswerController {
	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	QuestionRepository questionRepository;
	
	@Secured ({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/question/{questionID}/answersfull")
	public Page<Answer> getByQuestionIDfull(@PathVariable(value = "questionID") Long questionID, Pageable pageable) {
		return answerRepository.findByAnswerQuestionId(questionID, pageable);
	}
	
	@Secured ({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/question/{questionID}/answers")
	public Page<Answer> getByQuestionID(@PathVariable(value = "questionID") Long questionID, Pageable pageable) {
		return answerRepository.findByAnswerQuestionId(questionID, pageable).map(answer -> {
			answer.setAnswerIsCorrect(false);
			return answer;
		});
		
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/question/{questionID}/answer")
	public Answer createAnswer(@PathVariable(value = "questionID") Long questionID,
			@Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionID).map(question -> {
			answer.setAnswerQuestion(question);
			return answerRepository.save(answer);
		}).orElseThrow(() -> new EntityNotFoundException("questionID " + questionID + " not found"));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/question/{questionID}/answer/{answerID}")
	public Answer updateAnswer(@PathVariable(value = "questionID") Long questionID,
			@PathVariable(value = "answerID") Long answerID, @Valid @RequestBody Answer answerRequest) {
		if (!questionRepository.existsById(questionID)) {
			throw new EntityNotFoundException("questionID " + questionID + " not found");
		}

		return answerRepository.findById(answerID).map(answer -> {
			answer.setAnswerDescription(answerRequest.getAnswerDescription());
			answer.setAnswerIsCorrect(answerRequest.isAnswerIsCorrect());
			return answerRepository.save(answer);
		}).orElseThrow(() -> new EntityNotFoundException("answerID " + answerID + "not found"));
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/question/{questionID}/answer/{answerID}")
	public ResponseEntity<?> deleteAnswer(@PathVariable(value = "questionID") Long questionID,
			@PathVariable(value = "answerID") Long answerID) {
		if (!questionRepository.existsById(questionID)) {
			throw new EntityNotFoundException("questionID " + questionID + " not found");
		}

		return answerRepository.findById(answerID).map(answer -> {
			answerRepository.delete(answer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new EntityNotFoundException("answerID " + answerID + " not found"));
	}
}

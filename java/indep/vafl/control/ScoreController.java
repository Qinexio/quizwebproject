package indep.vafl.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import indep.vafl.datarepo.QuizRepository;
import indep.vafl.datarepo.ScoreRepository;
import indep.vafl.datarepo.UserRepository;
import indep.vafl.entity.Score;
import indep.vafl.service.EvaluationService;
import indep.vafl.service.StatisticService;
import indep.vafl.utility.EntityNotFoundException;
import indep.vafl.utility.Evaluation;
import indep.vafl.utility.StatUtility;

@RestController
public class ScoreController {
//user/{userID}/score
//quiz/{quizID}/score
//for creation use user/{userID}/quiz/{quizID}/score

	@Autowired
	ScoreRepository scoreRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	EvaluationService evalService;

	@Autowired
	StatisticService statisticService;

	@Secured("ROLE_ADMIN")
	@GetMapping("/quiz/{quizID}/scores")
	public Page<Score> getByQuizID(@PathVariable(value = "quizID") Integer quizID, Pageable pageable) {
		return scoreRepository.findByScoreQuizId(quizID, pageable);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/user/{userID}/scores")
	public Page<Score> getByUserID(@PathVariable(value = "userID") Integer userID, Pageable pageable) {
		return scoreRepository.findByScoreUserId(userID, pageable);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/score/{quizID}")
	public Page<StatUtility> scoreStats(@PathVariable(value = "quizID") Integer quizID, Pageable pageable) {
		
		if(!scoreRepository.findFirstByScoreQuizIdOrderByScoreDesc(quizID).isPresent())
			return Page.empty();
		
		int maxScore = scoreRepository.findFirstByScoreQuizIdOrderByScoreDesc(quizID).get().getScore();
		
		List<StatUtility> toAdd = new ArrayList<>();
		
		for(Integer i = maxScore; i>=0; i--)
		{
			StatUtility toInsert = new StatUtility();
			toInsert.setStatName(i.toString());
			toInsert.setStatVisits(scoreRepository.countByScoreAndScoreQuizId(i, quizID));
			toAdd.add(toInsert);
		}

		return new PageImpl<StatUtility>(toAdd);
	}

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/user/{userID}/quiz/{quizID}/score")
	public Score createScore(@PathVariable(value = "userID") Integer userID,
			@PathVariable(value = "quizID") Integer quizID, @Valid @RequestBody List<Evaluation> toEval) {

		Optional<Score> toTest = evalService.evaluateTest(toEval, quizID);

		if (toTest.isPresent()) {
			Score toCreate = toTest.get();

		return userRepository.findById(userID).map(user -> {
				toCreate.setScoreUser(user);
				return quizRepository.findById(quizID).map(quiz -> {
					toCreate.setScoreQuiz(quiz);
					statisticService.addStatistic(quizID);
					return scoreRepository.save(toCreate);
				}).orElseThrow(() -> new EntityNotFoundException("quizID " + quizID + " not found"));
			}).orElseThrow(() -> new EntityNotFoundException("userID " + userID + " not found"));
		}
		return null;
	}
}

package indep.vafl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indep.vafl.datarepo.AnswerRepository;
import indep.vafl.datarepo.QuestionRepository;
import indep.vafl.entity.Score;
import indep.vafl.utility.Evaluation;

@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Override
	public Optional<Score> evaluateTest(List<Evaluation> toEval, Integer testID) {
		Optional<Score> toReturn = Optional.empty();
		int scoreCount = 0;
		int storedAnswers;
		
		for (Evaluation eval : toEval) {
			if (questionRepository.findById(eval.getEvalQuestion()).get().getQuestionQuiz().getId() == testID
					.intValue()) {
				storedAnswers = eval.getEvalAnswers().size();
				for (Long answer : eval.getEvalAnswers()) {
					if (answerRepository.findById(answer).get().isAnswerIsCorrect())
						storedAnswers--;
				}
				if (storedAnswers == 0)
					scoreCount++;
			} else
				return toReturn;
		}

		Score toInsert = new Score();
		toInsert.setScore(scoreCount);

		toReturn = Optional.of(toInsert);
		return toReturn;

	}

}

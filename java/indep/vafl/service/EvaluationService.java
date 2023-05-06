package indep.vafl.service;

import java.util.List;
import java.util.Optional;

import indep.vafl.entity.Score;
import indep.vafl.utility.Evaluation;

public interface EvaluationService {

	Optional<Score> evaluateTest(List<Evaluation> toEval, Integer testID);
}

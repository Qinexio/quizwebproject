package indep.vafl.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import indep.vafl.datarepo.QuarterRepository;
import indep.vafl.datarepo.QuizRepository;
import indep.vafl.datarepo.StatisticRepository;
import indep.vafl.entity.Quarter;
import indep.vafl.entity.Stats;
import indep.vafl.utility.EntityNotFoundException;

@Service
public class StatisticServiceImpl implements StatisticService {

	@Autowired
	StatisticRepository statRepository;

	@Autowired
	QuarterRepository quarterRepository;

	@Autowired
	QuizRepository quizRepository;

	@Override
	public Stats addStatistic(Integer testID) {

		Optional<Quarter> currentQuarter = quarterRepository.findByQuarterMonthAndQuarterYear(
				Month.from(LocalDate.now()).name(), Year.from(LocalDate.now()).getValue());

		return statRepository.findByStatQuarterIdAndStatQuizId(currentQuarter.get().getId(), testID).map(stat -> {
			stat.setVisits(stat.getVisits() + 1);
			return statRepository.save(stat);
		}).orElseThrow(() -> new EntityNotFoundException("Test " + testID + " not found"));

	}

	@Override
	public Stats newStatistic(Integer testID) {
		Optional<Quarter> currentQuarter = quarterRepository.findByQuarterMonthAndQuarterYear(
				Month.from(LocalDate.now()).name(), Year.from(LocalDate.now()).getValue());

		Stats newStat = new Stats();
		
		newStat.setStatQuarter(currentQuarter.get());
		newStat.setVisits(0);
		newStat.setStatQuiz(quizRepository.findById(testID).get());

		return statRepository.save(newStat);
	}

}

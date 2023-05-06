package indep.vafl.control;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import indep.vafl.datarepo.QuarterRepository;
import indep.vafl.datarepo.StatisticRepository;
import indep.vafl.entity.Quarter;
import indep.vafl.entity.Stats;
import indep.vafl.utility.StatUtility;

@RestController
public class StatsController {
	@Autowired
	StatisticRepository statRepository;

	@Autowired
	QuarterRepository quarterRepository;

	@Secured("ROLE_ADMIN")
	@GetMapping("/stats/{quizID}")
	public Page<StatUtility> getByQuizId(@PathVariable(value = "quizID") Integer quizID, Pageable pageable) {
		Page<Stats> toProcess = statRepository.findByStatQuizId(quizID, pageable);

		List<StatUtility> toAdd = new ArrayList<>();

		toProcess.map(stat -> {
			StatUtility toInsert = new StatUtility();
			toInsert.setStatVisits(stat.getVisits());
			toInsert.setStatName(stat.getStatQuarter().getQuarterMonth()+" "+String.valueOf(stat.getStatQuarter().getQuarterYear()));
			return toAdd.add(toInsert);
		});

		return new PageImpl<StatUtility>(toAdd);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("stats/monthly")
	public PageImpl<StatUtility> getByMonth(Pageable pageable) {
		Month currentMonth = Month.from(LocalDate.now());
		Year currentYear = Year.from(LocalDate.now());

		Optional<Quarter> toFind = quarterRepository.findByQuarterMonthAndQuarterYear(currentMonth.name(),
				currentYear.getValue());

		Page<Stats> toProcess = statRepository.findByStatQuarterIdOrderByVisitsDesc(toFind.get().getId(), pageable);

		List<StatUtility> toAdd = new ArrayList<>();

		toProcess.map(stat -> {
			StatUtility toInsert = new StatUtility();
			toInsert.setStatVisits(stat.getVisits());
			toInsert.setStatName(stat.getStatQuiz().getQuizName());
			return toAdd.add(toInsert);
		});

		return new PageImpl<StatUtility>(toAdd);
	}
}

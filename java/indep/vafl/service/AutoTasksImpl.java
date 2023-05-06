package indep.vafl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import indep.vafl.datarepo.ProfileRepository;
import indep.vafl.datarepo.QuarterRepository;
import indep.vafl.datarepo.QuizRepository;
import indep.vafl.entity.Quarter;
import indep.vafl.entity.Quiz;

@Lazy(false)
@Component
public class AutoTasksImpl implements AutoTasks {

	@Autowired
	QuarterRepository quarterRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	StatisticService statService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	@Scheduled(cron = "0 5 0 1 * ?") // 0:05 at the start of each month (first day)
	public void addNewQuarter() {
		logger.warn("Progressing quarter.");
		Month currentMonth = Month.from(LocalDate.now());
		Year currentYear = Year.from(LocalDate.now());

		Quarter newQuarter = new Quarter();

		newQuarter.setQuarterMonth(currentMonth.name());
		newQuarter.setQuarterYear(currentYear.getValue());

		newQuarter = quarterRepository.save(newQuarter);
		List<Quiz> results = quizRepository.findAll();
		
		for(Quiz x : results)
		{
			statService.newStatistic(x.getId());
		} 
		
	}

	@Override
	@Scheduled(cron = "0 0 12 15 9 ? ") // 15 september, at 12:00 every year
	public void progressYear() {
		logger.warn("Progressing year.");
		profileRepository.findAll().stream().map(profile -> {
			if (profile.getProfileClass() < 13 || profile.isProfileIsRepeating() != true) {
				profile.setProfileClass(profile.getProfileClass() + 1);
			}
			profile.setProfileAge(profile.getProfileAge() + 1);
			return profileRepository.save(profile);
		});
	}

	@Override
	@PostConstruct
	public void verifyProgress() {
		logger.warn("Verifying database integrity.");

		Month currentMonth = Month.from(LocalDate.now());
		Year currentYear = Year.from(LocalDate.now());
		Optional<Quarter> toTest = quarterRepository.findByQuarterMonthAndQuarterYear(currentMonth.name(),
				currentYear.getValue());

		if (!toTest.isPresent())
			addNewQuarter();
	}

}

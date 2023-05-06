package indep.vafl.service;

import indep.vafl.entity.Stats;

public interface StatisticService {
	
	Stats addStatistic(Integer testID);
	Stats newStatistic(Integer testID);
}

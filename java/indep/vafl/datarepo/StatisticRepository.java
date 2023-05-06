package indep.vafl.datarepo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Stats;

@Repository 
public interface StatisticRepository extends JpaRepository<Stats,Long>{
	Page<Stats> findByStatQuizId(Integer testIdfk, Pageable pageable);
	Page<Stats> findByStatQuarterId(Long quarterIdfk, Pageable pageable);
	
	Optional<Stats> findByStatQuarterIdAndStatQuizId(Long quarterIdfk, Integer testIdfk);
	
	Page<Stats> findByStatQuarterIdOrderByVisitsDesc(Long quarterIdfk, Pageable pageable);
	

}

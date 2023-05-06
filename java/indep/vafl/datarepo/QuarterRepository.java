package indep.vafl.datarepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Quarter;

@Repository
public interface QuarterRepository extends JpaRepository<Quarter,Long> {
	
	Optional<Quarter> findByQuarterMonthAndQuarterYear(String quarterMonth, Integer quarterYear);

}

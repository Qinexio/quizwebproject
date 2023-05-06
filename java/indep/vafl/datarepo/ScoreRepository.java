package indep.vafl.datarepo;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Long>{
	Page<Score> findByScoreQuizId(Integer testIdfk, Pageable pageable);
	Page<Score> findByScoreUserId(Integer userIdfk, Pageable pageable);
	
	Page<Score> findByScoreQuizIdOrderByScoreDesc(Integer testIdfk, Pageable pageable);
	
	Optional<Score> findFirstByScoreQuizIdOrderByScoreDesc(Integer testIdfk);
	
	Long countByScoreAndScoreQuizId(Integer score, Integer testIdfk);
}

package indep.vafl.datarepo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer>{

	Page<Quiz> findByQuizValidation(Boolean bool, Pageable pageable);
}

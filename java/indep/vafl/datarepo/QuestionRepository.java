package indep.vafl.datarepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
	Page<Question> findByQuestionQuizId(Integer testIdfk, Pageable pageable);
}

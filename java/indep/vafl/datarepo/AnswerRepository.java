package indep.vafl.datarepo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import indep.vafl.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>
{
	Page<Answer> findByAnswerQuestionId(Long questionIdfk, Pageable pageable);
}
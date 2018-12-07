package dao;

import java.util.List;

import entity.Question;

public class QuestionDAO extends AbstractDAO implements IDao<Question, Long> {

	public QuestionDAO() {

	}

	@Override
	public List<Question> findAllEntities() {
		List<Question> Questions = (List<Question>) sessionUT.getSession().createQuery("from Question").list();
		return Questions;

	}

	@Override
	public Question findEntity(Long id) {
		Question book = (Question) sessionUT.getSession().get(Question.class, id);
		return book;

	}

	@Override
	public void createEntity(Question toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Question toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Question toDelete) {
		sessionUT.getSession().delete(toDelete);
	}
}

package dao;

import java.util.List;

import entity.Quiz;

public class QuizDAO extends AbstractDAO implements IDao<Quiz, Integer> {

	public QuizDAO() {

	}

	@Override
	public List<Quiz> findAllEntities() {
		List<Quiz> Quizs = (List<Quiz>) sessionUT.getSession().createQuery("from Quiz").list();
		return Quizs;

	}

	@Override
	public Quiz findEntity(Integer id) {
		Quiz book = (Quiz) sessionUT.getSession().get(Quiz.class, id);
		return book;

	}

	@Override
	public void createEntity(Quiz toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Quiz toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Quiz toDelete) {
		sessionUT.getSession().delete(toDelete);
	}
}

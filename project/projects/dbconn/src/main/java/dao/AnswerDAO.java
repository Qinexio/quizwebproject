package dao;

import java.util.List;

import entity.Answer;

public class AnswerDAO extends AbstractDAO implements IDao<Answer, Long> {

	public AnswerDAO() {

	}

	@Override
	public List<Answer> findAllEntities() {
		List<Answer> entities = (List<Answer>) sessionUT.getSession().createQuery("from Answer").list();
		return entities;

	}

	@Override
	public Answer findEntity(Long id) {
		Answer entity = (Answer) sessionUT.getSession().get(Answer.class, id);
		return entity;

	}

	@Override
	public void createEntity(Answer toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Answer toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Answer toDelete) {
		sessionUT.getSession().delete(toDelete);
	}

}

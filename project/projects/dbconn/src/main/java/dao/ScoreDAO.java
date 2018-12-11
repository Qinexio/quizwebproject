package dao;

import java.util.List;

import entity.Score;

public class ScoreDAO extends AbstractDAO implements IDao<Score, Long> {

	public ScoreDAO() {

	}

	@Override
	public List<Score> findAllEntities() {
		List<Score> entities = (List<Score>) sessionUT.getSession().createQuery("from Score").list();
		return entities;

	}

	@Override
	public Score findEntity(Long id) {
		Score entity = (Score) sessionUT.getSession().get(Score.class, id);
		return entity;

	}

	@Override
	public void createEntity(Score toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Score toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Score toDelete) {
		sessionUT.getSession().delete(toDelete);
	}

}

package dao;

import java.util.List;

import entity.Detail;

public class DetailDAO extends AbstractDAO implements IDao<Detail, Integer> {

	public DetailDAO() {

	}

	@Override
	public List<Detail> findAllEntities() {
		List<Detail> Details = (List<Detail>) sessionUT.getSession().createQuery("from Detail").list();
		return Details;

	}

	@Override
	public Detail findEntity(Integer id) {
		Detail book = (Detail) sessionUT.getSession().get(Detail.class, id);
		return book;

	}

	@Override
	public void createEntity(Detail toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Detail toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Detail toDelete) {
		sessionUT.getSession().delete(toDelete);
	}
}

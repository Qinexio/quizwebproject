package dao;

import java.util.List;

import entity.Credidential;

public class CredidentialDAO extends AbstractDAO implements IDao<Credidential, Integer> {

	public CredidentialDAO() {

	}

	@Override
	public List<Credidential> findAllEntities() {
		List<Credidential> Credidentials = (List<Credidential>) sessionUT.getSession().createQuery("from Credidential")
				.list();
		return Credidentials;

	}

	@Override
	public Credidential findEntity(Integer id) {
		Credidential book = (Credidential) sessionUT.getSession().get(Credidential.class, id);
		return book;

	}

	@Override
	public void createEntity(Credidential toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Credidential toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Credidential toDelete) {
		sessionUT.getSession().delete(toDelete);
	}

}

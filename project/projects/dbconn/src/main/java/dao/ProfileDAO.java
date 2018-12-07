package dao;

import java.util.List;

import entity.Profile;

public class ProfileDAO extends AbstractDAO implements IDao<Profile, Integer> {

	public ProfileDAO() {

	}

	@Override
	public List<Profile> findAllEntities() {
		List<Profile> Profiles = (List<Profile>) sessionUT.getSession().createQuery("from Profile").list();
		return Profiles;

	}

	@Override
	public Profile findEntity(Integer id) {
		Profile book = (Profile) sessionUT.getSession().get(Profile.class, id);
		return book;

	}

	@Override
	public void createEntity(Profile toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(Profile toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(Profile toDelete) {
		sessionUT.getSession().delete(toDelete);
	}

}

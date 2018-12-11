package dao;

import java.util.List;

import entity.User;

public class UserDAO extends AbstractDAO implements IDao<User, Integer> {

	public UserDAO() {

	}

	@Override
	public List<User> findAllEntities() {
		List<User> entities = (List<User>) sessionUT.getSession().createQuery("from User").list();
		return entities;

	}

	@Override
	public User findEntity(Integer id) {
		User entity = (User) sessionUT.getSession().get(User.class, id);
		return entity;

	}

	@Override
	public void createEntity(User toCreate) {
		sessionUT.getSession().persist(toCreate);
	}

	@Override
	public void updateEntity(User toUpdate) {
		sessionUT.getSession().update(toUpdate);

	}

	public void deleteEntity(User toDelete) {
		sessionUT.getSession().delete(toDelete);
	}

}

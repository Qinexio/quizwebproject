package service;

import org.hibernate.query.Query;

import dao.UserDAO;
import entity.Credidential;
import entity.Detail;
import entity.Profile;
import entity.User;

public class UserService {

	private static UserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();

	}
	//this might be stupid and need a role reversal(since User has 2 parents) from child(User)-parent(Credidential) to the other way around
	public User createUser(User toCreate, Credidential toAppend1, Profile toAppend2) {
		userDAO.getSessionUT().openSessionWithTransaction();
		toCreate.setUserCreds(toAppend1);
		toCreate.setUserProfile(toAppend2);
		toAppend1.setCredUser(toCreate);
		toAppend2.setProfileUser(toCreate);
		userDAO.createEntity(toCreate);
		userDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public User updateUser(User toUpdate) {
		userDAO.getSessionUT().openSessionWithTransaction();
		userDAO.updateEntity(toUpdate);
		userDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}

	
	public void deleteUser(int ID)
	{
		userDAO.getSessionUT().openSessionWithTransaction();
		Query<?> hibernateQuery = userDAO.getSessionUT().getSession().createQuery("from Iser as user where user.id = ?");
		User toDelete = (User) hibernateQuery.setParameter(0, ID);
		userDAO.deleteEntity(toDelete);
		userDAO.getSessionUT().closeSessionWithTransaction();
	}

}

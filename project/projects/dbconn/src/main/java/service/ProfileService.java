package service;

import dao.ProfileDAO;
import entity.Profile;

public class ProfileService {
	
	private static ProfileDAO profileDAO;

	public ProfileService() {
		profileDAO = new ProfileDAO();

	}

	public Profile createProfile(Profile toCreate) {
		profileDAO.getSessionUT().openSessionWithTransaction();
		profileDAO.createEntity(toCreate);
		profileDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public Profile updateProfile(Profile toUpdate) {
		profileDAO.getSessionUT().openSessionWithTransaction();
		profileDAO.updateEntity(toUpdate);
		profileDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
	


}

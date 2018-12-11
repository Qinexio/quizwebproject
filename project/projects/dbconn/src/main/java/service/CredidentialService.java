package service;


import dao.CredidentialDAO;
import entity.Credidential;

public class CredidentialService {
	
	private static CredidentialDAO credidentialDAO;

	public CredidentialService() {
		credidentialDAO = new CredidentialDAO();

	}

	public Credidential createCredidential(Credidential toCreate) {
		credidentialDAO.getSessionUT().openSessionWithTransaction();
		credidentialDAO.createEntity(toCreate);
		credidentialDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}

	public Credidential updateCredidential(Credidential toUpdate) {
		credidentialDAO.getSessionUT().openSessionWithTransaction();
		credidentialDAO.updateEntity(toUpdate);
		credidentialDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
	

}

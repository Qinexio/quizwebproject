package service;

import dao.DetailDAO;
import entity.Detail;

public class DetailService {

	private static DetailDAO detailDAO;
	
	public DetailService()
	{
		detailDAO = new DetailDAO();
	}
	
	public Detail createDetail(Detail toCreate)
	{
		detailDAO.getSessionUT().openSessionWithTransaction();
		detailDAO.createEntity(toCreate);
		detailDAO.getSessionUT().closeSessionWithTransaction();
		return toCreate;
	}
	
	public Detail updateDetail(Detail toUpdate)
	{
		detailDAO.getSessionUT().openSessionWithTransaction();
		detailDAO.updateEntity(toUpdate);
		detailDAO.getSessionUT().closeSessionWithTransaction();
		return toUpdate;
	}
	
	
	
}

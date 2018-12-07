package dao;

import java.util.List;

public interface IDao<T,ID> {
	
	public List<T> findAllEntities();
	public T findEntity(ID id);
	public void createEntity(T toCreate);
	public void updateEntity(T toUpdate);
	public void deleteEntity(T toDelete);
	
}

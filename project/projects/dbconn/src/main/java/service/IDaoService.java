package service;

import java.util.List;

public interface IDaoService<T,ID> {
	
	public List<T> findAll();
	public T find(ID id);
	public void create(T toCreate);
	public void update(T toUpdate);
	public void delete(ID idDelete);

}

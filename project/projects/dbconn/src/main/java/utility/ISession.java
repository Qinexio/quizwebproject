package utility;

import org.hibernate.Session;

public interface ISession {

	public Session openSession();
	public Session openSessionWithTransaction();
	public void closeSession();
	public void closeSessionWithTransaction();
	
}

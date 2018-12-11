package utility;

import org.hibernate.Session;
import org.hibernate.Transaction;

//object that handles sessions and transactions
public class SessionUtility implements ISession {

	private Session session;
	private Transaction transaction;

	public SessionUtility() {

	}

	@Override
	public Session openSession() {
		session = FactoryUtility.getSessionFactory().openSession();
		return session;
	}

	@Override
	public Session openSessionWithTransaction() {
		session = FactoryUtility.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		return session;

	}

	@Override
	public void closeSession() {
		session.close();
	}

	@Override
	public void closeSessionWithTransaction() {
		transaction.commit();
		session.close();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}

package utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//singleton class that configures and creates a singleton SessionFactory
public class FactoryUtility { 
	
	public static SessionFactory factory;

	private FactoryUtility() {
	}
	//synchronized use is in case of two objects requesting session factory at the same time, it's to avoid duplicates
	public static synchronized SessionFactory getSessionFactory() {

		if (factory == null) {
			factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return factory;
	}
}

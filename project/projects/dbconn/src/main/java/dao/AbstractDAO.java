package dao;

import utility.SessionUtility;

abstract class AbstractDAO {

	protected SessionUtility sessionUT;

	public SessionUtility getSessionUT() {
		return sessionUT;
	}

	public void setSessionUT(SessionUtility sessionUT) {
		this.sessionUT = sessionUT;
	}

}

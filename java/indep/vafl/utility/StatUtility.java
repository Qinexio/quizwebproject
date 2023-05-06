package indep.vafl.utility;

import java.io.Serializable;

public class StatUtility implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long statVisits;
	private String statName;
	
	public long getStatVisits() {
		return statVisits;
	}
	public void setStatVisits(long statVisits) {
		this.statVisits = statVisits;
	}
	public String getStatName() {
		return statName;
	}
	public void setStatName(String statName) {
		this.statName = statName;
	}

}

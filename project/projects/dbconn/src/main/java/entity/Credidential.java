package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userCredidentials")
public class Credidential implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4601039778689536689L;

	@Id
	@GeneratedValue
	@Column(name = "credID")
	private int id;

	@Column(name = "credPass")
	private String credPassword;

	@Column(name = "credSalt")
	private String credSalt;

	@OneToOne(mappedBy = "userCreds", fetch = FetchType.LAZY)
	private User credUser;

	public Credidential() {

	}

	public Credidential(int id, String credPassword, String credSalt) {
		super();
		this.id = id;
		this.credPassword = credPassword;
		this.credSalt = credSalt;
	}

	
	public Credidential(int id, String credPassword, String credSalt, User credUser) {
		super();
		this.id = id;
		this.credPassword = credPassword;
		this.credSalt = credSalt;
		this.credUser = credUser;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credidential other = (Credidential) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Credidential [id=" + id + ", credPassword=" + credPassword + ", credSalt=" + credSalt + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCredPassword() {
		return credPassword;
	}

	public void setCredPassword(String credPassword) {
		this.credPassword = credPassword;
	}

	public String getCredSalt() {
		return credSalt;
	}

	public void setCredSalt(String credSalt) {
		this.credSalt = credSalt;
	}

	public User getCredUser() {
		return credUser;
	}

	public void setCredUser(User credUser) {
		this.credUser = credUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

}
package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userBase")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7892418481913055870L;

	@Id
	@GeneratedValue
	@Column(name = "userID")
	private int id;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userMail")
	private String userMail;

	@Column(name = "userConfirmation")
	private String userConfirmation;
	
	@Column(name = "userIsVerified")
	private boolean userIsVerified;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "extIDfk", referencedColumnName = "extID")
	private Profile userProfile;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "credIDfk", referencedColumnName = "credID")
	private Credidential userCreds;
	
	@OneToMany(mappedBy = "scoreUser", fetch = FetchType.LAZY)
	private List<Score> userScores;
	
	public User()
	{

		}
	
	

	public User(int id, String userName, String userMail, String userConfirmation, boolean userIsVerified,
			Profile userProfile, Credidential userCreds, List<Score> userScores) {
		super();
		this.id = id;
		this.userName = userName;
		this.userMail = userMail;
		this.userConfirmation = userConfirmation;
		this.userIsVerified = userIsVerified;
		this.userProfile = userProfile;
		this.userCreds = userCreds;
		this.userScores = userScores;
	}

	

	public User(int id, String userName, String userMail, String userConfirmation, boolean userIsVerified) {
		super();
		this.id = id;
		this.userName = userName;
		this.userMail = userMail;
		this.userConfirmation = userConfirmation;
		this.userIsVerified = userIsVerified;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userMail=" + userMail + ", userConfirmation="
				+ userConfirmation + ", userIsVerified=" + userIsVerified + "]";
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserConfirmation() {
		return userConfirmation;
	}

	public void setUserConfirmation(String userConfirmation) {
		this.userConfirmation = userConfirmation;
	}

	public boolean isUserIsVerified() {
		return userIsVerified;
	}

	public void setUserIsVerified(boolean userIsVerified) {
		this.userIsVerified = userIsVerified;
	}

	public Profile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(Profile userProfile) {
		this.userProfile = userProfile;
	}

	public Credidential getUserCreds() {
		return userCreds;
	}

	public void setUserCreds(Credidential userCreds) {
		this.userCreds = userCreds;
	}

	public List<Score> getUserScores() {
		return userScores;
	}

	public void setUserScores(List<Score> userScores) {
		this.userScores = userScores;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

	

}

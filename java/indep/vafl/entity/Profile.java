package indep.vafl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "userProfile")
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3088271801332671258L;

	@Id
	@SequenceGenerator(name="seqGenProfile", sequenceName = "userProfile_extID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenProfile")
	@Column(name = "extID")
	private int id;

	@NotNull
	@Column(name = "extName")
	private String profileName;
	
	@NotNull
	@Column(name = "extAge")
	private int profileAge;
	
	@NotNull
	@Column(name = "extGender")
	private String profileGender;

	@NotNull
	@Column(name = "extClass")
	private int profileClass;
	
	@Column(name = "extIsRepeating") 
	private boolean profileIsRepeating;

	@OneToOne(optional = false, fetch = FetchType.LAZY,orphanRemoval=true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userIDfk", referencedColumnName = "userID")
	@JsonIgnore
	private User profileUser;

	public Profile() {

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
		Profile other = (Profile) obj;
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
	
	public boolean isProfileIsRepeating() {
		return profileIsRepeating;
	}


	public void setProfileIsRepeating(boolean profileIsRepeating) {
		this.profileIsRepeating = profileIsRepeating;
	}

 
	public int getProfileAge() {
		return profileAge;
	}


	public void setProfileAge(int profileAge) {
		this.profileAge = profileAge;
	}


	public String getProfileGender() {
		return profileGender;
	}


	public void setProfileGender(String profileGender) {
		this.profileGender = profileGender;
	}


	public int getProfileClass() {
		return profileClass;
	}


	public void setProfileClass(int profileClass) {
		this.profileClass = profileClass;
	}


	public User getProfileUser() {
		return profileUser;
	}

	public void setProfileUser(User profileUser) {
		this.profileUser = profileUser;
	}

	public String getProfileName() {
		return profileName;
	}


	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	};

}

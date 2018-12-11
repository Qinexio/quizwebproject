package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	@Column(name = "extDescription")
	private String profileDescription;

	@Column(name = "extImage", unique = true)
	private String profileImageLocation;

	@Column(name = "extImageExtension")
	private String profileImageExtension;

	@OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
	private User profileUser;

	public Profile() {

	}

	public Profile(String profileDescription, String profileImageLocation, String profileImageExtension) {
		super();
		this.profileDescription = profileDescription;
		this.profileImageLocation = profileImageLocation;
		this.profileImageExtension = profileImageExtension;
	}

	public Profile(int id, String profileDescription, String profileImageLocation, String profileImageExtension,
			User profileUser) {
		super();
		this.id = id;
		this.profileDescription = profileDescription;
		this.profileImageLocation = profileImageLocation;
		this.profileImageExtension = profileImageExtension;
		this.profileUser = profileUser;
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

	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileDescription=" + profileDescription + ", profileImageLocation="
				+ profileImageLocation + ", profileImageExtension=" + profileImageExtension + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public String getProfileImageLocation() {
		return profileImageLocation;
	}

	public void setProfileImageLocation(String profileImageLocation) {
		this.profileImageLocation = profileImageLocation;
	}

	public String getProfileImageExtension() {
		return profileImageExtension;
	}

	public void setProfileImageExtension(String profileImageExtension) {
		this.profileImageExtension = profileImageExtension;
	}

	public User getProfileUser() {
		return profileUser;
	}

	public void setProfileUser(User profileUser) {
		this.profileUser = profileUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

}

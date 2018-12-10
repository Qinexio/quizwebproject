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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "testBase")
public class Quiz implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799815714173249899L;

	@Id
	@SequenceGenerator(name="seqGenQuiz", sequenceName = "testBase_testID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenQuiz")
	@Column(name = "testID")
	private int id;

	@Column(name = "testName")
	private String quizName;

	@Column(name = "testDescription")
	private String quizDescription;

	@Column(unique = true)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "userIDfk", referencedColumnName = "userID")
	private Profile quizUser;
	
	@Column(unique = true)
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@Cascade(value= CascadeType.ALL)
	@JoinColumn(name = "detIDfk", referencedColumnName = "detID")
	private Detail quizDetail;

	@OneToMany(mappedBy = "scoreQuiz", fetch = FetchType.LAZY)
	@Cascade(value= CascadeType.ALL)
	private List<Score> quizScores;
	
	@OneToMany(mappedBy = "questionQuiz", fetch = FetchType.LAZY)
	@Cascade(value= CascadeType.ALL)
	private List<Question> quizQuestions;
	
	public Quiz()
	{

		}

	
	
	public Quiz(int id, String quizName, String quizDescription, Profile quizUser, Detail quizDetail,
			List<Score> quizScores, List<Question> quizQuestions) {
		super();
		this.id = id;
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.quizUser = quizUser;
		this.quizDetail = quizDetail;
		this.quizScores = quizScores;
		this.quizQuestions = quizQuestions;
	}



	public Quiz(int id, String quizName, String quizDescription) {
		super();
		this.id = id;
		this.quizName = quizName;
		this.quizDescription = quizDescription;
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
		Quiz other = (Quiz) obj;
		if (id != other.id)
			return false;
		return true;
	}
	


	@Override
	public String toString() {
		return "Quiz [id=" + id + ", quizName=" + quizName + ", quizDescription=" + quizDescription + "]";
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public String getQuizDescription() {
		return quizDescription;
	}

	public void setQuizDescription(String quizDescription) {
		this.quizDescription = quizDescription;
	}

	public Profile getQuizUser() {
		return quizUser;
	}

	public void setQuizUser(Profile quizUser) {
		this.quizUser = quizUser;
	}

	public Detail getQuizDetail() {
		return quizDetail;
	}

	public void setQuizDetail(Detail quizDetail) {
		this.quizDetail = quizDetail;
	}

	public List<Score> getQuizScores() {
		return quizScores;
	}

	public void setQuizScores(List<Score> quizScores) {
		this.quizScores = quizScores;
	}

	public List<Question> getQuizQuestions() {
		return quizQuestions;
	}

	public void setQuizQuestions(List<Question> quizQuestions) {
		this.quizQuestions = quizQuestions;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};

	
}

package indep.vafl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "testBase")
public class Quiz extends EntityDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 799815714173249899L;

	@Id
	@SequenceGenerator(name = "seqGenQuiz", sequenceName = "testBase_testID_seq", initialValue = 1, allocationSize = 3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqGenQuiz")
	@Column(name = "testID")
	private int id;
	
	@NotNull
	@Column(name = "testName")
	private String quizName;
	
	@NotNull
	@Column(name = "testDescription")
	private String quizDescription;

	@NotNull
	@Column(name = "testValidation")
	private boolean quizValidation;

	public Quiz() {

	}

	public Quiz(int id, String quizName, String quizDescription, boolean quizValidation) {
		super();
		this.id = id;
		this.quizName = quizName;
		this.quizDescription = quizDescription;
		this.quizValidation = quizValidation;
	}

	public Quiz(String quizName, String quizDescription, boolean quizValidation) {
		super();
		this.quizValidation = quizValidation;
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean getValidation() {
		return quizValidation;
	}

	public void setValidation(boolean b) {
		this.quizValidation = b;

	};

}

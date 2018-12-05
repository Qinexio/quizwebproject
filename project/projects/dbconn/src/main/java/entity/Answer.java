package entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "testAnswer")
public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917546024071036747L;

	@Id
	@GeneratedValue
	@Column(name = "answerID")
	private long id;

	@Column(name = "answerDescription")
	private String answerDescription;

	@Column(name = "answerIsCorrect")
	private boolean answerIsCorrect;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionIDfk",  referencedColumnName = "questionID")
    private Question answerQuestion;

	public Answer()
	{

		}

	
	public Answer(long id, String answerDescription, boolean answerIsCorrect) {
		super();
		this.id = id;
		this.answerDescription = answerDescription;
		this.answerIsCorrect = answerIsCorrect;
	}

	

	public Answer(long id, String answerDescription, boolean answerIsCorrect, Question answerQuestion) {
		super();
		this.id = id;
		this.answerDescription = answerDescription;
		this.answerIsCorrect = answerIsCorrect;
		this.answerQuestion = answerQuestion;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Answer other = (Answer) obj;
		if (id != other.id)
			return false;
		return true;
	}

	

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerDescription=" + answerDescription + ", answerIsCorrect=" + answerIsCorrect
				+ "]";
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswerDescription() {
		return answerDescription;
	}

	public void setAnswerDescription(String answerDescription) {
		this.answerDescription = answerDescription;
	}

	public boolean isAnswerIsCorrect() {
		return answerIsCorrect;
	}

	public void setAnswerIsCorrect(boolean answerIsCorrect) {
		this.answerIsCorrect = answerIsCorrect;
	}

	public Question getAnswerQuestion() {
		return answerQuestion;
	}

	public void setAnswerQuestion(Question answerQuestion) {
		this.answerQuestion = answerQuestion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};


}

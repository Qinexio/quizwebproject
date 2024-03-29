package indep.vafl.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "testAnswer")
public class Answer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -917546024071036747L;
	//might have to raise allocation size to improve performance, even though more gaps will appear
	@Id
	@SequenceGenerator(name="seqGenAnswer", sequenceName = "testAnswer_answerID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenAnswer")
	@Column(name = "answerID")
	private long id;

	@NotNull
	@Column(name = "answerDescription")
	private String answerDescription;

	@NotNull
	@Column(name = "answerIsCorrect")
	private boolean answerIsCorrect;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "questionIDfk",  referencedColumnName = "questionID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Question answerQuestion;

	public Answer()
	{

		}

	
	public Answer(String answerDescription, boolean answerIsCorrect) {
		super();
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

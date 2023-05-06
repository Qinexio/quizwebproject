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
@Table(name = "testQuestion")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5776437534614436755L;

	@Id
	@SequenceGenerator(name="seqGenQuestion", sequenceName = "testQuestion_questionID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenQuestion")
	@Column(name = "questionID")
	private long id;

	@NotNull
	@Column(name = "questionDescription")
	private String questionDescription;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "testIDfk",  referencedColumnName = "testID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
    private Quiz questionQuiz;
	

	public Question()
	{

		}
	
	public Question(String questionDescription) {
		super();
		this.questionDescription = questionDescription;
	}

	
	public Question(long id, String questionDescription, Quiz questionQuiz) {
		super();
		this.id = id;
		this.questionDescription = questionDescription;
		this.questionQuiz = questionQuiz;
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
		Question other = (Question) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionDescription=" + questionDescription + ", questionScore="
				 + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	public Quiz getQuestionQuiz() {
		return questionQuiz;
	}

	public void setQuestionQuiz(Quiz questionQuiz) {
		this.questionQuiz = questionQuiz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};


}

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

	@Column(name = "questionDescription")
	private String questionDescription;

	@Column(name = "questionScore")
	private double questionScore;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "testIDfk",  referencedColumnName = "testID")
    private Quiz questionQuiz;
	
	@OneToMany(mappedBy = "answerQuestion")
	@Cascade(value= CascadeType.ALL)
	private List<Answer> questionAnswers;

	public Question()
	{

		}
	
	public Question(long id, String questionDescription, double questionScore) {
		super();
		this.id = id;
		this.questionDescription = questionDescription;
		this.questionScore = questionScore;
	}

	
	public Question(long id, String questionDescription, double questionScore, Quiz questionQuiz,
			List<Answer> questionAnswers) {
		super();
		this.id = id;
		this.questionDescription = questionDescription;
		this.questionScore = questionScore;
		this.questionQuiz = questionQuiz;
		this.questionAnswers = questionAnswers;
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
				+ questionScore + "]";
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

	public double getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(double questionScore) {
		this.questionScore = questionScore;
	}

	public Quiz getQuestionQuiz() {
		return questionQuiz;
	}

	public void setQuestionQuiz(Quiz questionQuiz) {
		this.questionQuiz = questionQuiz;
	}

	public List<Answer> getQuestionAnswers() {
		return questionAnswers;
	}

	public void setQuestionAnswers(List<Answer> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};


}

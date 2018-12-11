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
@Table(name = "testDetail")
public class Detail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6013286209964435897L;

	@Id
	@SequenceGenerator(name="seqGenDetail", sequenceName = "testDetail_detID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenDetail")
	@Column(name = "detID")
	private int id;

	@Column(name = "detQuestionLimit")
	private int detailQuestionLimit;

	@Column(name = "detAnswerLimit")
	private int detailAnswerLimit;

	@Column(name ="detMercy")
	private boolean detailIsMercy;
	
	@Column(name ="detTime")
	private int detailTime;
	
	@OneToOne(mappedBy = "quizDetail", fetch = FetchType.LAZY)
	private Quiz detailQuiz;

	
	public Detail()
	{

		}

	
	
	public Detail(int detailQuestionLimit, int detailAnswerLimit, boolean detailIsMercy, int detailTime) {
		super();
		this.detailQuestionLimit = detailQuestionLimit;
		this.detailAnswerLimit = detailAnswerLimit;
		this.detailIsMercy = detailIsMercy;
		this.detailTime = detailTime;
	}

	

	public Detail(int id, int detailQuestionLimit, int detailAnswerLimit, boolean detailIsMercy, int detailTime,
			Quiz detailQuiz) {
		super();
		this.id = id;
		this.detailQuestionLimit = detailQuestionLimit;
		this.detailAnswerLimit = detailAnswerLimit;
		this.detailIsMercy = detailIsMercy;
		this.detailTime = detailTime;
		this.detailQuiz = detailQuiz;
	}

	

	@Override
	public String toString() {
		return "Detail [id=" + id + ", detailQuestionLimit=" + detailQuestionLimit + ", detailAnswerLimit="
				+ detailAnswerLimit + ", detailIsMercy=" + detailIsMercy + ", detailTime=" + detailTime + "]";
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
		Detail other = (Detail) obj;
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


	public int getDetailQuestionLimit() {
		return detailQuestionLimit;
	}


	public void setDetailQuestionLimit(int detailQuestionLimit) {
		this.detailQuestionLimit = detailQuestionLimit;
	}


	public int getDetailAnswerLimit() {
		return detailAnswerLimit;
	}


	public void setDetailAnswerLimit(int detailAnswerLimit) {
		this.detailAnswerLimit = detailAnswerLimit;
	}


	public boolean isDetailIsMercy() {
		return detailIsMercy;
	}


	public void setDetailIsMercy(boolean detailIsMercy) {
		this.detailIsMercy = detailIsMercy;
	}


	public int getDetailTime() {
		return detailTime;
	}


	public void setDetailTime(int detailTime) {
		this.detailTime = detailTime;
	}


	public Quiz getDetailQuiz() {
		return detailQuiz;
	}


	public void setDetailQuiz(Quiz detailQuiz) {
		this.detailQuiz = detailQuiz;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	};

	

}


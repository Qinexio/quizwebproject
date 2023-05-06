package indep.vafl.utility;

import java.io.Serializable;
import java.util.List;

public class Evaluation implements Serializable{

	/**
	 * 
	 */
	private Long evalQuestion;
	private List<Long> evalAnswers;
	
	
	private static final long serialVersionUID = 1L;
	
	public Evaluation() {
		super();
	}


	public Evaluation(Long evalQuestion, List<Long> evalAnswers) {
		super();
		this.evalQuestion = evalQuestion;
		this.evalAnswers = evalAnswers;
	}


	public Long getEvalQuestion() {
		return evalQuestion;
	}


	public void setEvalQuestion(Long evalQuestion) {
		this.evalQuestion = evalQuestion;
	}


	public List<Long> getEvalAnswers() {
		return evalAnswers;
	}


	public void setEvalAnswers(List<Long> evalAnswers) {
		this.evalAnswers = evalAnswers;
	}
	
	
	
	
}
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
@Table(name = "userScore")
public class Score extends EntityDate implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778596812815454452L;
	@Id
	@SequenceGenerator(name="seqGenScore", sequenceName = "userScore_scoreID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenScore")
	@Column(name = "scoreID")
	private long id;

	@NotNull
	@Column(name = "score")
	private int score;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userIDfk",  referencedColumnName = "userID")
	@JsonIgnore
    private User scoreUser;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "testIDfk",  referencedColumnName = "testID")
	@JsonIgnore
    private Quiz scoreQuiz;

	public Score()
	{

		}

	
	public Score(int score, User scoreUser, Quiz scoreQuiz) {
		super();
		this.score = score;
		this.scoreUser = scoreUser;
		this.scoreQuiz = scoreQuiz;
	}


	public Score(long id, int score) {
		super();
		this.id = id;
		this.score = score;
	}


	@Override
	public String toString() {
		return "Score [id=" + id + ", score=" + score + "]";
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
		Score other = (Score) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public User getScoreUser() {
		return scoreUser;
	}

	public void setScoreUser(User scoreUser) {
		this.scoreUser = scoreUser;
	}

	public Quiz getScoreQuiz() {
		return scoreQuiz;
	}

	public void setScoreQuiz(Quiz scoreQuiz) {
		this.scoreQuiz = scoreQuiz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	};
		
	


}

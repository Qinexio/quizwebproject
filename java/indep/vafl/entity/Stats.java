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
@Table(name = "testStatistic")
public class Stats extends EntityDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778596812815454452L;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVisits() {
		return visits;
	}

	public void setVisits(long visits) {
		this.visits = visits;
	}

	public Quarter getStatQuarter() {
		return statQuarter;
	}

	public void setStatQuarter(Quarter statQuarter) {
		this.statQuarter = statQuarter;
	}

	public Quiz getStatQuiz() {
		return statQuiz;
	}

	public void setStatQuiz(Quiz statQuiz) {
		this.statQuiz = statQuiz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@SequenceGenerator(name="seqGenStatistic", sequenceName = "testStatistic_statID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenStatistic")
	@Column(name = "statID")
	private long id;

	@NotNull
	@Column(name = "statVisits")
	private long visits;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "quarterIDfk",  referencedColumnName = "quarterID")
	@JsonIgnore
    private Quarter statQuarter;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "testIDfk",  referencedColumnName = "testID")
	@JsonIgnore
    private Quiz statQuiz;

}
		
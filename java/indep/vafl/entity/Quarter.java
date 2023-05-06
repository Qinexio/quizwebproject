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
@Table(name = "dateQuarter")
public class Quarter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8778596812815454452L;
	@Id
	@SequenceGenerator(name="seqGenQuarter", sequenceName = "dateQuarter_quarterID_seq", initialValue=1, allocationSize=3)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqGenQuarter")
	@Column(name = "quarterID")
	private long id;

	@NotNull
	@Column(name = "quarterYear")
	private int quarterYear;
	
	@NotNull
	@Column(name = "quarterMonth")
	private String quarterMonth;
	
	public Quarter()
	{
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuarterYear() {
		return quarterYear;
	}

	public void setQuarterYear(int year) {
		this.quarterYear = year;
	}

	public String getQuarterMonth() {
		return quarterMonth;
	}

	public void setQuarterMonth(String month) {
		this.quarterMonth = month;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
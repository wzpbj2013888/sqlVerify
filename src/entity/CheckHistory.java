package entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sqlCheckHistory")
public class CheckHistory {
	
	@SequenceGenerator(name = "sqlCheckHistory_seq", sequenceName = "sqlCheckHistory_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqlCheckHistory_seq")
	
	private int id;
	private int taskId;
	private Date submitDate;
	private Date feedBackDate;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	public Date getFeedBackDate() {
		return feedBackDate;
	}
	public void setFeedBackDate(Date feedBackDate) {
		this.feedBackDate = feedBackDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
}

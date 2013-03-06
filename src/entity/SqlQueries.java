package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SqlQueries")
public class SqlQueries {

	@SequenceGenerator(name = "SqlQueries_seq", sequenceName = "SqlQueries_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SqlQueries_seq")
	private int id;
	private String sqlStatement;
	private int historyId;
	private int sqlId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public int getSqlId() {
		return sqlId;
	}

	public void setSqlId(int sqlId) {
		this.sqlId = sqlId;
	}
}

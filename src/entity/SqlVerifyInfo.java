package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sqlverify_verifyinfo")
public class SqlVerifyInfo {

	@SequenceGenerator(name = "sqlverify_info", sequenceName = "sqlverify_info")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqlverify_info")
	private int id;
	private int changeId;
	private String changeState;
	private String statementId;
	private String sqlStatement;
	private String optimizer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChangeId() {
		return changeId;
	}

	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}

	public String getChangeState() {
		return changeState;
	}

	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}


	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public String getSqlStatement() {
		return sqlStatement;
	}

	public void setSqlStatement(String sqlStatement) {
		this.sqlStatement = sqlStatement;
	}

	public String getOptimizer() {
		return optimizer;
	}

	public void setOptimizer(String optimizer) {
		this.optimizer = optimizer;
	}

}

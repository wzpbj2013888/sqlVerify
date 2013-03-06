package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sqlexecutionplan")
public class ExecutionPlanInfo {
	@SequenceGenerator(name = "sqlexecutionplan_seq", sequenceName = "sqlexecutionplan_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqlexecutionplan_seq")
	private int id;
	private int orderBy;
	private String object_owner;
	private String object_name;
	private String operation;
	private int cost;
	private int carddinality;
	private int bytes;
	private int time;
	private int io_cost;
	private int historyId;
	private int sqlId;
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
	private String database;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getObject_owner() {
		return object_owner;
	}
	public void setObject_owner(String object_owner) {
		this.object_owner = object_owner;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCarddinality() {
		return carddinality;
	}
	public void setCarddinality(int carddinality) {
		this.carddinality = carddinality;
	}
	public int getBytes() {
		return bytes;
	}
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getIo_cost() {
		return io_cost;
	}
	public void setIo_cost(int io_cost) {
		this.io_cost = io_cost;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

}

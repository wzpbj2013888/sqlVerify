package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "sqlverify_sqlchangeinfo")
public class ChangeInfo {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@SequenceGenerator(name = "sqlverify_sqlchangeinfo_seq", sequenceName = "sqlverify_sqlchangeinfo_seq")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sqlverify_sqlchangeinfo_seq")
	
	private int id;
	private String name;
	private String owner;
	private String state;
}

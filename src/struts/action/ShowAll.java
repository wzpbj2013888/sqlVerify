package struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;

import spring.dao.JdbcDaoImpl;

public class ShowAll {
	private JdbcDaoImpl dao;
	private List allUsers;
	private int totalCount;
	private int start;
	private int page;
	private String entityName;
	private String statementId;

	@JSON(serialize = false)
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	@JSON(serialize = false)
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JSON(serialize = false)
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	private int limit;

	public String execute() {
		List all = null;
		int total = 0;
		if (getEntityName().equals("ExecutionPlanInfo")) {
			all = getDao().getAllExecutionPlanInfo(getStart(),
					getPage() * getLimit(),getStatementId());
			total = getDao().getCountExecutionPlanInfo(getStatementId());
		} else if(getEntityName().equals("User")){
			all = getDao().getAllUser(getStart(), getPage() * getLimit());
			total = getDao().getCount();
		}
		
		setAllUsers(all);
		setTotalCount(total);
		return Action.SUCCESS;
	}

	public List getAllUsers() {
		// getDao().batchInsert();
		return allUsers;
	}

	public void setAllUsers(List allUsers) {
		this.allUsers = allUsers;
	}

	@JSON(serialize = false)
	public JdbcDaoImpl getDao() {
		return dao;
	}

	public void setDao(JdbcDaoImpl dao) {
		this.dao = dao;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@JSON(serialize = false)
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@JSON(serialize = false)
	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

}

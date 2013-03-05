package struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import spring.dao.JdbcDaoImpl;

import com.opensymphony.xwork2.Action;

public class ShowTaskList {

	private JdbcDaoImpl dao;
	private List allUsers;
	private long totalCount;
	private int start;
	private int page;
	private String changeNo;
	private String taskName;
	private String status;
	@JSON(serialize = false)
	public String getChangeNo() {
		return changeNo;
	}

	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}
	@JSON(serialize = false)
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	@JSON(serialize = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
		long total = 0;
		all = getDao().getTaskList(getStart(),getPage() * getLimit(),getChangeNo(),getTaskName(),getStatus());
		
		total = getDao().getCountTaskList();
		
		setAllUsers(all);
		setTotalCount(total);
		
		
		return Action.SUCCESS;
	}

	@JSON(serialize = false)
	public JdbcDaoImpl getDao() {
		return dao;
	}

	public void setDao(JdbcDaoImpl dao) {
		this.dao = dao;
	}

	public List getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List allUsers) {
		this.allUsers = allUsers;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long total) {
		this.totalCount = total;
	}

}

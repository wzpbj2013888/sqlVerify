package struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import spring.dao.JdbcDaoImpl;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ShowVerifyHistory extends ActionSupport {

	private JdbcDaoImpl dao;
	private List allUsers;
	private int totalCount;
	private int start;
	private int page;
	private int taskId;
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
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
		int total = 0;
		all = getDao().getVerifyHistory(getStart(),getPage() * getLimit(),getTaskId());
		
		total = getDao().getCountVerifyHistory();
		
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}

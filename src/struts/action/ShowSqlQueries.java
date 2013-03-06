package struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import spring.dao.JdbcDaoImpl;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class ShowSqlQueries extends ActionSupport {

	private JdbcDaoImpl dao;
	private List allUsers;
	private int totalCount;
	private int start;
	private int page;
	private int historyId;

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
		all = getDao().getSqlQueries(getStart(), getPage() * getLimit(),
				getHistoryId());

		total = getDao().getCountSqlQueries();

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

	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}
}

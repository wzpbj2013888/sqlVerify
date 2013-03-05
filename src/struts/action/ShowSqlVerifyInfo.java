package struts.action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import spring.dao.JdbcDaoImpl;

import com.opensymphony.xwork2.Action;

public class ShowSqlVerifyInfo {

	private JdbcDaoImpl dao;
	private List verifyInfo;
	private int totalCount;
	private int start;
	private int page;

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
		List all = getDao().getAllSqlVerifyInfo(getStart(), getPage() * getLimit());
		setVerifyInfo(all);
		setTotalCount(getDao().getCountSqlVerifyInfo());
		return Action.SUCCESS;
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


	public List getVerifyInfo() {
		return verifyInfo;
	}

	public void setVerifyInfo(List verifyInfo) {
		this.verifyInfo = verifyInfo;
	}
}

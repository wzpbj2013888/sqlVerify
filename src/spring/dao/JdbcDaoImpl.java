package spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class JdbcDaoImpl extends JdbcDaoSupport {
	private SessionFactory sessionFactory;
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		if (this.hibernateTemplate == null) {
			this.hibernateTemplate = new HibernateTemplate(sessionFactory);
		}
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void batchInsert() {
		for (int i = 1; i < 8888; i++) {
			String sql = "insert into WUZHIPING_SQLVERIFY_USER1 values (" + i
					+ ",'name" + i + "','company" + i + "','address" + i + "')";
			this.getJdbcTemplate().update(sql);
		}
	}

	public List getAllUser(final int start, final int end) {
		List result = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0.createQuery("FROM entity.User");
						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});

		return result;
	}

	public List getAllChangeInfo(final int start, final int end) {
		List result = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0
								.createQuery("FROM entity.ChangeInfo");
						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});
		return result;
	}

	public List getAllSqlVerifyInfo(final int start, final int end) {
		List result = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						Query query = arg0
								.createQuery("FROM entity.SqlVerifyInfo");
						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});
		return result;
	}

	public int getCountSqlVerifyInfo() {
		String sql = "select count(*) from  sqlverify_verifyinfo";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public List getAllExecutionPlanInfo(final int start, final int end,
			final String statementId) {
		List result = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						String hql = (statementId == null) ? "FROM entity.ExecutionPlanInfo"
								: "FROM entity.ExecutionPlanInfo en where en.statementId='"
										+ statementId + "'";

						Query query = arg0.createQuery(hql);

						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}
				});
		return result;
	}

	public int getCountExecutionPlanInfo(final String statementId) {

		String sql = (statementId == null) ? "select count(*) from  sqlexecutionplan"
				: "select count(*) from  sqlexecutionplan where statementId='"
						+ statementId + "'";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public void batchInsertChangeInfo() {
		for (int i = 1; i < 8888; i++) {
			String sql = "insert into sqlverify_sqlchangeinfo values (" + i
					+ ",'吴志平" + i + "','银行转账系统补丁" + i + "','未通过" + i + "')";
			this.getJdbcTemplate().update(sql);
		}
	}

	public int getCountChangeInfo() {
		String sql = "select count(*) from  sqlverify_sqlchangeinfo";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public void nothing() {

	}

	public int getCount() {
		String sql = "select count(*) from  WUZHIPING_SQLVERIFY_USER1";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List getTaskList(final int start, final int end,
			final String changeNo, final String taskName, final String status) {
		List result = this.getHibernateTemplate().executeFind(
				
				
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						
						String changeNoCriteria = (changeNo.equals("")) ? ""
								: "and name like '%" + changeNo + "%'";
						String taskNameCriteria = (taskName.equals("")) ? ""
								: "and name like '%" + taskName + "%'";
						String statusCriteria = (status.equals("")) ? ""
								: "and name like '%" + status + "%'";
						Query query = arg0
								.createQuery("FROM entity.SqlVerifyTask where 1=1 "
										+ changeNoCriteria
										+ taskNameCriteria
										+ statusCriteria);

						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});
		return result;
	}

	public long getCountTaskList() {
		List ls = getHibernateTemplate().find(
				"select count(*)from entity.SqlVerifyTask");
		
		// TODO Auto-generated method stub
		return (Long)ls.get(0);
	}

	public List getVerifyHistory(final int start, final int end,final int taskId) {


		List result = this.getHibernateTemplate().executeFind(
				
				
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						
						
						
						Query query = arg0
								.createQuery("FROM entity.CheckHistory where taskId = " + taskId);

						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});
		return result;
	}

	public int getCountVerifyHistory() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List getSqlQueries(final int start, final int end,final int historyId) {


		List result = this.getHibernateTemplate().executeFind(
				
				
				new HibernateCallback() {

					public List doInHibernate(Session arg0)
							throws HibernateException, SQLException {
						


						Query query = arg0
								.createQuery("FROM entity.SqlQueries where historyId = " + historyId);

						query.setMaxResults(end - start);
						query.setFirstResult(start);
						return query.list();
					}

				});
		return result;
	}

	public int getCountSqlQueries() {
		// TODO Auto-generated method stub
		return 0;
	}
}

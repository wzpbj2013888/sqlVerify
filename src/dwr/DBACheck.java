package dwr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.dao.JdbcDaoImpl;

public class DBACheck {
	private JdbcDaoImpl dao;

	public DBACheck() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		 dao = ctx.getBean("jdbcDaoimpl", JdbcDaoImpl.class);
	}

	public void passCheck(String statementId) {
		getDao().getJdbcTemplate().update("update sqlverify_verifyinfo set changeState = 'passed' where statementId = '"+statementId+"'");
	}

	public void failCheck(String statementId) {
		getDao().getJdbcTemplate().update("update sqlverify_verifyinfo set changeState = 'not passed' where statementId = '"+statementId+"'");
	}

	public JdbcDaoImpl getDao() {
		return dao;
	}

	public void setDao(JdbcDaoImpl dao) {
		this.dao = dao;
	}
}

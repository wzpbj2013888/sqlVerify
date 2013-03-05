package dwr;

import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.dao.JdbcDaoImpl;

public class GetSqlById {
	
	public String getSql(String statementId) {

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		JdbcDaoImpl dao = ctx.getBean("jdbcDaoimpl", JdbcDaoImpl.class);
		
		
		String sql = "select sqlStatement from sqlverify_verifyinfo where statementId='"+statementId+"'";
		System.out.println(sql);
		String s= (String) dao.getJdbcTemplate().queryForObject(sql, java.lang.String.class);
		return s;
	}
	public static void main(String[] args) {
		new GetSqlById().getSql("");
	}
}

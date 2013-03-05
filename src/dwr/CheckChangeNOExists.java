package dwr;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.dao.JdbcDaoImpl;
import spring.ftp.I.FtpI;
import spring.ftp.imp.FtpImp;

public class CheckChangeNOExists {
	private JdbcDaoImpl jdbcDaoImpl;
	private FtpI ftpImp = new FtpImp();


	public String queryChangeNo(String changeNo) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		this.jdbcDaoImpl = ctx.getBean("jdbcDaoimpl", JdbcDaoImpl.class);
		
		int checkDB;
		String sql = "select count(*) from sqlverify_verifyinfo where changeId = "
				+ changeNo + " and changestate = 'not checked'";

		System.out.println(sql);

		try {
			checkDB = this.jdbcDaoImpl.getJdbcTemplate().queryForInt(sql);
		} catch (Exception e) {
			return "sqlError0";
		}
		
		if (checkDB > 0) {
			return "notChecked";
		} else {
			
			String isExit = "notExists";
			
			try {
				ftpImp.setFtp("localhost", 21, "slh", "ncl@1234");
				isExit = ftpImp.queryChangeNo(changeNo);
			} catch (IOException io) {
				io.printStackTrace();
			}

			return isExit.equals("exists") ? "exists" : "notExists";
		}

	}
}

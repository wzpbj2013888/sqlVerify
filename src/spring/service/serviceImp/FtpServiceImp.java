package spring.service.serviceImp;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import spring.dao.JdbcDaoImpl;
import spring.dao.JdbcDaoImpl1;
import spring.ftp.I.FtpI;
import spring.ftp.imp.FtpImp;
import spring.service.serviceI.FtpServiceI;
import tool.FormatSql;

public class FtpServiceImp implements FtpServiceI {
	private JdbcDaoImpl jdbcDaoImpl;
	private JdbcDaoImpl1 jdbcDaoImpl1;

	public JdbcDaoImpl getJdbcDaoImpl() {
		return jdbcDaoImpl;
	}

	public void setJdbcDaoImpl(JdbcDaoImpl jdbcDaoImpl) {
		this.jdbcDaoImpl = jdbcDaoImpl;
	}

	FtpI ftpImp = new FtpImp();

	public FtpI getFtpImp() {
		return ftpImp;
	}

	public void setFtpImp(FtpI ftpImp) {
		this.ftpImp = ftpImp;
	}

	/**
	 * 检查变更号是否存在
	 * 
	 * @see com.service.serviceI.FtpServiceI#queryChangeNo(java.lang.String)
	 */
	public String queryChangeNo(String changeNo) {
		// TODO Auto-generated method stub

		int checkDB;
		String sql = "select count(*) from sqlverify_verifyinfo where changeId = "
				+ changeNo + " and changestate = 'not checked'";

		try {
			checkDB = jdbcDaoImpl.getJdbcTemplate().queryForInt(sql);
			System.out.println(checkDB);
		} catch (Exception e) {
			System.out.println(e);
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

	/**
	 * 获取变更号文档中的sql
	 * 
	 * @see com.service.serviceI.FtpServiceI#getSql(java.lang.String)
	 */
	public String[] getSql(String changeNo) {
		try {
			ftpImp.setFtp("localhost", 21, "slh", "ncl@1234");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new String[] { e.getMessage() };
		}
		return ftpImp.getSql(changeNo);
	}

	/*
	 * 执行sql语句 param ChangeNo 变更号 param sqls 变更文件中的sql语句数组
	 */
	public String executeSql(String changeNo, String[] sqls, String optimizer) {
		FormatSql format = new FormatSql();
		String[] ids;
		long time = 0;
		String[] orgSql = sqls.clone();

		String saveTask = "insert into sqlverify_task values(sqlverify_task_seq.nextval,'"
				+ changeNo + "-" + "变更名','submitted')";

		jdbcDaoImpl.getJdbcTemplate().execute(saveTask);

		int taskId = jdbcDaoImpl.getJdbcTemplate().queryForInt(
				"select sqlverify_task_seq.currval from dual");
		String saveTaskHistory = "insert into sqlCheckHistory(id,taskId,submitDate,feedBackDate,status) values (sqlCheckHistory_seq.nextval,"
				+ taskId + ", sysdate,null,'已提交验证申请')";
		jdbcDaoImpl.getJdbcTemplate().execute(saveTaskHistory);

		int sqlCheckHistoryId = jdbcDaoImpl.getJdbcTemplate().queryForInt(
				"select sqlCheckHistory_seq.currval from dual");

		ids = format.formate(sqls, changeNo);
		for (int i = 0; i < sqls.length; i++) {
			try {

				long beginTime = System.currentTimeMillis();
				jdbcDaoImpl.getJdbcTemplate().execute(sqls[i]);
				long endTime = System.currentTimeMillis();
				time = endTime - beginTime;

				String sql = "select id orderBy, bytes,cardinality,cost,'开发库' database,io_cost,object_name,object_owner,operation,statement_id, "
						+ time
						+ " time	 from plan_table1 where statement_id ='"
						+ ids[i] + "'";

				List<Map<String, Object>> ls = jdbcDaoImpl.getJdbcTemplate()
						.queryForList(sql);

				for (Map<String, Object> o : ls) {
					String insert = "insert into SQLEXECUTIONPLAN (ID,BYTES,CARDDINALITY,COST,DATABASE,HISTORYID,IO_COST,OBJECT_NAME,OBJECT_OWNER,OPERATION,ORDERBY,SQLID,TIME) values (sqlexecutionplan_seq.nextval,"
							+ o.get("bytes")
							+ ","
							+ o.get("cardinality")
							+ ","
							+ o.get("cost")
							+ ",'"
							+ o.get("database")
							+ "',"
							+ sqlCheckHistoryId
							+ ",'"
							+ o.get("io_cost")
							+ "','"
							+ o.get("object_name")
							+ "','"
							+ o.get("object_owner")
							+ "','"
							+ o.get("operation")
							+ "',"
							+ o.get("orderBy")
							+ ","
							+ i
							+ ","
							+ o.get("time") + ")";
					jdbcDaoImpl.getJdbcTemplate().execute(insert);
				}
				/*
				 * 测试库上执行
				 */

				beginTime = System.currentTimeMillis();
				jdbcDaoImpl1.getJdbcTemplate().execute(sqls[i]);
				endTime = System.currentTimeMillis();
				time = endTime - beginTime;

				String sql1 = "select id orderBy,  id,bytes,cardinality,cost,'测试库' database,io_cost,object_name,object_owner,operation,statement_id, "
						+ time
						+ " time	 from plan_table1 where statement_id ='"
						+ ids[i] + "'";

				List<Map<String, Object>> ls1 = jdbcDaoImpl1.getJdbcTemplate()
						.queryForList(sql1);

				for (Map<String, Object> o : ls1) {
					String insert = "insert into SQLEXECUTIONPLAN (ID,BYTES,CARDDINALITY,COST,DATABASE,HISTORYID,IO_COST,OBJECT_NAME,OBJECT_OWNER,OPERATION,ORDERBY,SQLID,TIME) values (sqlexecutionplan_seq.nextval,"
							+ o.get("bytes")
							+ ","
							+ o.get("cardinality")
							+ ","
							+ o.get("cost")
							+ ",'"
							+ o.get("database")
							+ "',"
							+ sqlCheckHistoryId
							+ ",'"
							+ o.get("io_cost")
							+ "','"
							+ o.get("object_name")
							+ "','"
							+ o.get("object_owner")
							+ "','"
							+ o.get("operation")
							+ "',"
							+ o.get("orderBy")
							+ ","
							+ i
							+ ","
							+ o.get("time") + ")";
					jdbcDaoImpl.getJdbcTemplate().execute(insert);
				}

				String saveSqlClause = "insert into sqlverify_verifyinfo (id,changeId,changeState,statementId,optimizer,sqlStatement) values(sqlverify_info.nextval,"
						+ changeNo
						+ ",'not passed','"
						+ ids[i]
						+ "','"
						+ optimizer
						+ "','"
						+ orgSql[i].replaceAll("'", "''")
						+ "')";

				jdbcDaoImpl.getJdbcTemplate().execute(saveSqlClause);

				String saveSqlQuery = "insert into SqlQueries(id,sqlStatement,historyId,sqlId) values (SqlQueries_seq.nextval,'"
						+ orgSql[i].replaceAll("'", "''")
						+ "',"
						+ sqlCheckHistoryId + "," + i + ")";

				jdbcDaoImpl.getJdbcTemplate().execute(saveSqlQuery);

			} catch (Exception e) {
				return e.getMessage();
			}

		}
		return "success";
	}

	public JdbcDaoImpl1 getJdbcDaoImpl1() {
		return jdbcDaoImpl1;
	}

	public void setJdbcDaoImpl1(JdbcDaoImpl1 jdbcDaoImpl1) {
		this.jdbcDaoImpl1 = jdbcDaoImpl1;
	}

	public String executeSql(String changeNo, String[] sqls) {
		// TODO Auto-generated method stub
		return null;
	}

}

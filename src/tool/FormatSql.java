package tool;

import java.sql.Timestamp;
import java.util.Calendar;

public class FormatSql {
	public String[] formate(String[] sqls, String changeNo) {
		String[] ids = new String[sqls.length];
		for (int i = 0; i < sqls.length; i++) {
			ids[i] = changeNo + "_" + System.currentTimeMillis() + "_" + i;
			sqls[i] = "explain plan set statement_id = '" + ids[i]
					+ "' into plan_table1 for select /*+ all_rows */ * "
					+ sqls[i].substring(sqls[i].indexOf("from"));
		}
		return ids;
	}
}

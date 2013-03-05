package spring.service.serviceI;

public interface FtpServiceI {
	public String queryChangeNo(String changeNo);

	public String[] getSql(String changeNo);
	
	public String executeSql(String changeNo,String [] sqls, String string);
}

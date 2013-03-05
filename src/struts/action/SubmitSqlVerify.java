package struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import spring.service.serviceI.FtpServiceI;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.entities.ActionConfig;

public class SubmitSqlVerify extends ActionSupport {
	private String submitter;
	private String changeName;
	private String changeNo;
	private FtpServiceI ftpService;
	private String execSqlResult;
	private String flag;
	private String optimizer;
	
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getExecSqlResult() {
		return execSqlResult;
	}
	public void setExecSqlResult(String execSqlResult) {
		this.execSqlResult = execSqlResult;
	}
	public FtpServiceI getFtpService() {
		return ftpService;
	}
	public void setFtpService(FtpServiceI ftpService) {
		this.ftpService = ftpService;
	}
	public String getSubmitter() {
		return submitter;
	}
	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getChangeNo() {
		return changeNo;
	}
	public void setChangeNo(String changeNo) {
		this.changeNo = changeNo;
	}
	 String[] getSql(String verifyNo){
	
		return  ftpService.getSql(verifyNo) ;
	}
	public String directExecuteSql(){
		HttpServletRequest request=ServletActionContext.getRequest();
		String tempSql[]=this.getSql(this.changeNo);
		if(!(tempSql[0].toUpperCase().startsWith("SELECT"))){
			execSqlResult =tempSql[0];
			flag="1";
			request.setAttribute("flag", flag);
			return "error";
		}
		String result=ftpService.executeSql(changeNo, tempSql, getOptimizer());
		if(result.equals("success")){
			return "success" ;
		}else{
			execSqlResult=result;
			flag="2";
			request.setAttribute("flag", flag);
			return "error";
		}
	
	}
	public String uploadExecuteSql(){
		return null;
	}
	public String getOptimizer() {
		return optimizer;
	}
	public void setOptimizer(String optimizer) {
		this.optimizer = optimizer;
	}
}

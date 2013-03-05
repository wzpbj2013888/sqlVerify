package struts.action;

import com.opensymphony.xwork2.Action;

public class SubmitSql {

	private String submitter;
	private String changeId;
	private String changeName;
	private String changeAttachment1 ="17860.rar";

	public String execute() {
		if(getSubmitter()!=null){
			
			return Action.SUCCESS;
		}
		
		return Action.INPUT;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getChangeId() {
		return changeId;
	}

	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getChangeAttachment() {
		return changeAttachment1;
	}

	public void setChangeAttachment(String changeAttachment) {
		this.changeAttachment1 = changeAttachment;
	}

	private String changeAttachment;

}

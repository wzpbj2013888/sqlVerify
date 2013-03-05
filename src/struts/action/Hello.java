package struts.action;

import com.opensymphony.xwork2.Action;

public class Hello {
	private String message;

	public String execute() {

		return Action.SUCCESS;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package struts.action.login.action;


import struts.action.login.model.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private boolean success;
	private String message;
	private User user;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (user.getUsername().equals("admin")
				&& user.getPassword().equals("admin")) {
			this.success = true;
			// this.message="����˺��ǣ�"+user.getUsername()+"�����ǣ�"+user.getPassword();
		} else {
			this.success = false;
			this.message = "�Բ���δ��Ȩ���û����ܵ�¼��ϵͳ";
		}
		ActionContext.getContext().getSession().put("name", user.getUsername());
		return SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
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
			// this.message="你的账号是："+user.getUsername()+"密码是："+user.getPassword();
		} else {
			this.success = false;
			this.message = "对不起，未授权的用户不能登录改系统";
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
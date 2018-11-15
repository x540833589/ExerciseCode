package cn.com.shoppingmall.form;

import java.util.Hashtable;
import java.util.Map;

public class LoginForm implements ActionForm{

    private String username;    //用户名
    private String password;    //密码

    private Map<String, String> errorMessages = new Hashtable<>();  //错误信息提示列表

    @Override
    public Map<String, String> validate() {
        if(username.length() < 3 || username.length() > 10)
            errorMessages.put("username", "用户名格式错误.");
        if(password.length() < 3 || password.length() > 10)
            errorMessages.put("password", "密码格式错误.");
        return errorMessages;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}
    
}

package cn.com.shoppingmall.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class RegisterForm implements ActionForm{

    private String username;                    //用户名
    private String password;                    //密码
    private String nickname;                    //昵称
    private String trueName;                    //真实姓名
    private String sex;                         //性别
    private String dateOfBirthStringValue = ""; //出生日期(字符串、中间值)
    @SuppressWarnings("unused")
	private java.sql.Date dateOfBirth;          //出生日期(日期类型、向后台传递的真实值)
    private String address;                     //住址
    private String phoneNumber;                 //联系电话
    private String email;                       //邮箱
    private String IDNumber;                    //身份证号码

    private Map<String, String> errorMessages = new Hashtable<>();  //错误信息提示列表

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Map<String, String> validate() {
        if(username.length() > 10 || username.length() < 3)
            errorMessages.put("username", "用户名输入非法.");
        if(password.length() > 10 || password.length() < 3)
            errorMessages.put("password", "密码输入非法.");
        if(nickname.length() > 10 || nickname.length() < 3)
            errorMessages.put("nickname", "昵称输入非法.");
        if(trueName.length() > 10 || trueName.length() < 2)
            errorMessages.put("trueName", "真实姓名输入非法.");
        if(!sex.equals("男") && !sex.equals("女") && !sex.equals("male") && !sex.equals("female"))
            errorMessages.put("sex", "性别输入非法.");
	        try {
	        	if (dateOfBirthStringValue.equals("")) {
	        		errorMessages.put("dateOfBirthStringValue", "出生日期输入非法.");
				}else if(getDateOfBirth().after(new Date()))
	                errorMessages.put("dateOfBirthStringValue", "出生日期输入非法.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        if(address.length() > 50 || address.length() < 1)
            errorMessages.put("address", "住址输入非法.");
        if(phoneNumber.length() != 11)
            errorMessages.put("phoneNumber", "联系电话输入非法.");
        if(email.length() > 50 || email.length() < 1 || !email.contains("@") || (!email.endsWith(".com") && !email.endsWith(".cn") && !email.endsWith(".org")))
            errorMessages.put("email", "邮箱输入非法.");
        if(IDNumber.length() != 18)
            errorMessages.put("IDNumber", "身份证号输入非法.");
        return errorMessages;
    }

    /**
     * 将注册表单中前端传递过来的字符串类型的出生日期(dateOfString)转换成java.sql.Date类型，以便存入数据库
     * @return 转换后的Date类型的出生日期
     */
    public java.sql.Date getDateOfBirth() {
        java.sql.Date date = null;
        try {
        	if(!dateOfBirthStringValue.equals(""))
        		date = new java.sql.Date(simpleDateFormat.parse(dateOfBirthStringValue).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDateOfBirthStringValue() {
		return dateOfBirthStringValue;
	}

	public void setDateOfBirthStringValue(String dateOfBirthStringValue) {
		this.dateOfBirthStringValue = dateOfBirthStringValue;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String iDNumber) {
		IDNumber = iDNumber;
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Map<String, String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}

	public void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
		this.simpleDateFormat = simpleDateFormat;
	}

	public void setDateOfBirth(java.sql.Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
    

}

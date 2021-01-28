package com.baizhi.entity;

public class User {
	//通过需求分析分析出实体需要声明的属性（字段）
	private String username;
	private String password;
	private String email;
	private String phone;
	//判断是否可以登陆
	private String status; //yes 正常登陆  no 冻结
	//来源于哪个平台
	private String systemSource;
	//注册时间  便于随后排序
	private  String registerDate;
	
	//无参构造方法
	//有参构造方法
	//get和set方法
	//toString方法
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String email, String phone,
			String status, String systemSource, String registerDate) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.systemSource = systemSource;
		this.registerDate = registerDate;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", email=" + email + ", phone=" + phone + ", status="
				+ status + ", systemSource=" + systemSource + ", registerDate="
				+ registerDate + "]";
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSystemSource() {
		return systemSource;
	}
	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	
	
}

package com.baizhi.entity;

public class Record {
	private String username;
	//最后一次登陆IP
	private String finalip;
	//最后一次使用本系统的时间
	private String finalTime;
	//来源于哪个平台
	private String systemSource;
	//注册时间 
	private String registerDate;
	
	//Record r = new Record();
	
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Record r = new Record("","","","","");
	public Record(String username, String finalip, String finalTime,
			String systemSource, String registerDate) {
		super();
		this.username = username;
		this.finalip = finalip;
		this.finalTime = finalTime;
		this.systemSource = systemSource;
		this.registerDate = registerDate;
		
	}
	
	//System.out.println(r);
	@Override
	public String toString() {
		return "Record [username=" + username + ", finalip=" + finalip
				+ ", finalTime=" + finalTime + ", systemSource=" + systemSource
				+ ", registerDate=" + registerDate + "]";
	}
	
	
	//private get/set
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFinalip() {
		return finalip;
	}
	public void setFinalip(String finalip) {
		this.finalip = finalip;
	}
	public String getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(String finalTime) {
		this.finalTime = finalTime;
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

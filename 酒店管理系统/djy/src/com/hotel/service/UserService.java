package com.baizhi.service;

import java.util.ArrayList;

import com.baizhi.entity.Record;
import com.baizhi.entity.User;

public interface UserService {
	//用户注册（插入）
	public void insertUserService(User user);
	
	//用户登陆(String 区分多种挑战情况)
	public String loginService(String username,String password);
	
	public ArrayList<User> queryAllUserService();
	//查询某个用户的所有记录
	public ArrayList<Record> queryAllRecordService(String username);
	//删除方法
	public void deleteUserService(String username);
		
	//修改用户状态
	public void updateUserStatusService(String username,String status);
	
	//删除记录
	public void deleteRecordService(String username,String finalTime);
}

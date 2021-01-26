package com.baizhi.dao;

import java.util.ArrayList;

import com.baizhi.entity.Record;
import com.baizhi.entity.User;

public interface UserDao {
	//插入用户
	public void insertUserDao(User user);
	
	//通过username的一个查询
	public User queryUserByUsername(String username);
	
	//登陆成功的同时，要出入一条该用户的记录
	public void insertRecordDao(Record record);
	
	//展示users表中的所有用户信息
	public ArrayList<User>  queryAllUser();
	
	//通过用户名查询对应用户的登陆记录
	public ArrayList<Record> queryAllRecord(String username);
	
	//删除方法
	public void deleteUserDao(String username);
	
	//修改用户状态
	 public void updateUserStatusDao(String username,String status);
	 
	 //删除记录
	 public void deleteRecordDao(String username,String finalTime);
}











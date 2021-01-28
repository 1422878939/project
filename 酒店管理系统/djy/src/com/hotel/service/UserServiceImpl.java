package com.baizhi.service;

import java.util.ArrayList;

import com.baizhi.dao.UserDao;
import com.baizhi.dao.UserDaoImpl;
import com.baizhi.entity.Record;
import com.baizhi.entity.User;

public class UserServiceImpl implements UserService {

	public void insertUserService(User user) {
		//注册没有逻辑判断
		//嵌套dao层
		UserDao dao = new UserDaoImpl();
		dao.insertUserDao(user);
	}

	public String loginService(String username, String password) {
		//嵌套dao
		UserDao dao = new UserDaoImpl();
		User user = dao.queryUserByUsername(username);
		
		if(user == null){
			return "noUser";
		}else if(password.equals("")){
			return "noPass";
		}else if(!password.equals(user.getPassword())){
			return "errorPass";
		}else if(!user.getStatus().equals("yes")){
			return "errorStatus";
		}else{
			return "yes";
		}
	}

	public ArrayList<User> queryAllUserService() {
		//嵌套dao层,将userlist传递给servlet
		UserDao dao = new UserDaoImpl();
		ArrayList<User> userlist = dao.queryAllUser();
		
		return userlist;
	}

	public ArrayList<Record> queryAllRecordService(String username) {
		UserDao dao = new UserDaoImpl();
		ArrayList<Record> recordlist = dao.queryAllRecord(username);
		return recordlist;
	}

	public void deleteUserService(String username) {
		UserDao dao = new UserDaoImpl();
		dao.deleteUserDao(username);
		
	}

	public void updateUserStatusService(String username, String status) {
		UserDao dao = new UserDaoImpl();
		dao.updateUserStatusDao(username, status);
	}

	public void deleteRecordService(String username, String finalTime) {
		UserDao dao = new UserDaoImpl();
		dao.deleteRecordDao(username, finalTime);
		
	}

}












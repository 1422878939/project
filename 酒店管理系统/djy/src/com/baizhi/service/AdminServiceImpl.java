package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AdminDaoImpl;
import com.baizhi.entity.Admin;

public class AdminServiceImpl implements AdminService{

	public String adminloginService(String adminName, String adminPass) {
		//嵌套dao
		AdminDao dao = new AdminDaoImpl();
		Admin admin = dao.queryAdminByUsername(adminName);
				
		if(admin == null){
			return "noAdmin";
		}else if(!adminPass.equals(admin.getAdminPass())){
			return "adminErrorPass";
		}else{
			return "yes";
		}
	}

}

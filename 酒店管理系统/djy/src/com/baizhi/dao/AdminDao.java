package com.baizhi.dao;

import com.baizhi.entity.Admin;
import com.baizhi.entity.User;

public interface AdminDao {
	//通过username的一个查询
		public Admin queryAdminByUsername(String adminName);
}

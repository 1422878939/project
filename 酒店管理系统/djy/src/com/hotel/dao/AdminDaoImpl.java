package com.baizhi.dao;

import com.alibaba.fastjson.JSON;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.util.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class AdminDaoImpl implements AdminDao{

	public Admin queryAdminByUsername(String adminName) {
		DBCollection dbCollection = MongoDBUtil.getCollection("admin");
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("adminName",adminName);
		DBObject  findOneObject =  dbCollection.findOne(usernameObject);
		Admin admin =JSON.parseObject(JSON.toJSONString(findOneObject),Admin.class);
				
		return admin;
	}

}

package com.baizhi.util;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBUtil {
	//static  MongoDBUtil._______
	public static DBCollection getCollection(String name){
		try {
			
			//链接Mongo
			MongoClient client = new MongoClient("127.0.0.1",27017);
			//获取访问的目标数据库
			DB db = client.getDB("baizhi");
			//通过传递的name 得到对应的collection
			DBCollection dbCollection = db.getCollection(name);
			//返回表
			return dbCollection;
			
		} catch (Exception e) {
			//打印异常信息
			e.printStackTrace();
			//异常信息的反馈
			throw new RuntimeException("获取collection失败！");
		}
		
	}
}





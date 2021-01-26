package com.baizhi.dao;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.baizhi.entity.Record;
import com.baizhi.entity.User;
import com.baizhi.util.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class UserDaoImpl implements UserDao{

	public void insertUserDao(User user) {
		//java驱动操作MongoDB的步骤
		//链接mongo
        //从mongo中得到baizhi数据库
		//从baizhi数据库中得到user表（Collection）
		DBCollection dbCollection = MongoDBUtil.getCollection("users");
		//{username:"",password:"",email:"",...}
		DBObject userObject = new BasicDBObject();
		userObject.put("username",user.getUsername());
		userObject.put("password",user.getPassword());
		userObject.put("email",user.getEmail());
		userObject.put("phone",user.getPhone());
		userObject.put("status",user.getStatus());
		userObject.put("systemSource",user.getSystemSource());
		userObject.put("registerDate",user.getRegisterDate());
		
		//插入 users 表
		dbCollection.insert(userObject);
	}

	public User queryUserByUsername(String username) {
		//工具类 MongoDBUtil 拿表
		DBCollection dbCollection = MongoDBUtil.getCollection("users");
		//{username:""}
		//db.users.find({username:""})
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username",username);
		//进行条件查询
		DBObject  findOneObject =  dbCollection.findOne(usernameObject);
		//findOneObject表示的只是MongoDB中的对象，（json）
		//而不是java中对象形式，转换成java对象的形式
		//两个参数：
		//1.对象的json串形式（String）
		//2.转换成的对象的形式
		User user =JSON.parseObject(JSON.toJSONString(findOneObject),User.class);
		
		return user;
	}
	//插入当前用户登陆的一条记录
	public void insertRecordDao(Record record) {
		DBCollection userRecordCollection = MongoDBUtil.getCollection("userRecord");
		
		DBObject userRecordObject = new BasicDBObject();
		userRecordObject.put("username",record.getUsername());
		userRecordObject.put("finalip",record.getFinalip());
		userRecordObject.put("finalTime",record.getFinalTime());
		userRecordObject.put("systemSource",record.getSystemSource());
		userRecordObject.put("registerDate",record.getRegisterDate());
		//执行插入操作
		userRecordCollection.insert(userRecordObject);
		
		//五个数据
		//username 从页面获得
		//finalTime  (Date date=new Date();  -> sdf)
		
		//registerDate 、systemSource 
		//通过username查询返回一个user -> get() ->registerDate 、systemSource 
		
		//finalip -> request.getRemoteAddr(); -> 返回 ip地址
		
	}
	
	//从MongoDB 中获取users表中的所有用户信息
	public ArrayList<User> queryAllUser() {
		//声明称创建一个集合
		ArrayList<User> userlist = new ArrayList<User>();
		DBCollection usersCollection = MongoDBUtil.getCollection("users");
		//怎么查询所有用户信息（DBCursor父类是ArrayList）
		DBCursor usersCursor = usersCollection.find();
		for (DBObject dbObject : usersCursor) {
			//JSON.toJSONString(dbObject):把mongo对象转成成 json串
			//JSON.parseObject(__,___);把json串转换成 java对象
			  //注意java对象类型后.class( 底层是反射 )
			User user = JSON.parseObject(JSON.toJSONString(dbObject),User.class);
			//把转换成功的对象窜入java的ArrayList中
			userlist.add(user);
		}
		//返回，为了传递给service  -> 传递给servlet程序 -> jsp
		return userlist;
	}

	public ArrayList<Record> queryAllRecord(String username) {
		//连接Mongo ,拿表（userRecord）
		DBCollection userRecordCollection = MongoDBUtil.getCollection("userRecord");
		//{username:_____}
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username",username);
		//获得MongoDB集合
		DBCursor userRecordCursor = userRecordCollection.find(usernameObject);
		//Mongo集合 ->  java集合
		//DBCursor -> ArrayList
		ArrayList<Record> recordlist = new ArrayList<Record>();
		for (DBObject dbObject : userRecordCursor) {
		Record record = JSON.parseObject(JSON.toJSONString(dbObject),Record.class);
		//把转变类型后的对象存入ArrayList集合
		recordlist.add(record);
		}
		
		return recordlist;
	}

	public void deleteUserDao(String username) {
		DBCollection usersCollection = MongoDBUtil.getCollection("users");
		
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username", username);
		usersCollection.remove(usernameObject);
	}

	public void updateUserStatusDao(String username,String status) {
		DBCollection usersCollection = MongoDBUtil.getCollection("users");
		
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username", username);
		if("yes".equals(status)){
			DBObject statusObject = new BasicDBObject();
			statusObject.put("status","no");
			DBObject setObject = new BasicDBObject();
			setObject.put("$set", statusObject);
			usersCollection.update(usernameObject, setObject, false, true);
		}else{
			DBObject statusObject = new BasicDBObject();
			statusObject.put("status","yes");
			DBObject setObject = new BasicDBObject();
			setObject.put("$set", statusObject);
			usersCollection.update(usernameObject, setObject, false, true);
		}
	}

	public void deleteRecordDao(String username,String finalTime) {
		DBCollection usersCollection = MongoDBUtil.getCollection("userRecord");
		
		/*DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username", username);
		DBObject timeObject = new BasicDBObject();
		timeObject.put("finalTime",finalTime);
		
		DBObject conObject = new BasicDBObject();
		conObject.putAll(conObject);*/
		DBObject usernameObject = new BasicDBObject();
		usernameObject.put("username", username);
		usernameObject.put("finalTime",finalTime);
		
		usersCollection.remove(usernameObject);
		
	}

}









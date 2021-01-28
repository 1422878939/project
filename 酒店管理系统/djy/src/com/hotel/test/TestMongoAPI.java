package com.baizhi.test;

import java.util.ArrayList;
import java.util.List;

import com.baizhi.util.MongoDBUtil;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class TestMongoAPI {
	private Mongo mongo;
	private DB db;
	private DBCollection dbCollection;
	//初始化
	public void init() throws Exception{
		//创建链接对象
		mongo = new Mongo("127.0.0.1",27017);
		//获取数据库对象
		db = mongo.getDB("baizhi");
		//获取表对象(Collection)
		dbCollection = MongoDBUtil.getCollection("users");
	}
	//插入对象
	public void insert(){
		//{name:"JoJo",age:16,salary:600000}
		DBObject dbObject = new BasicDBObject();
		dbObject.put("name","JoJo");
		dbObject.put("age",16);
		dbObject.put("salary","600000");
		dbCollection.insert(dbObject);
	}
	//插入集合
	//{people:["xiaos","zhaos","wangw"]}
	public void insertList(){
		BasicDBList dbList = new BasicDBList();
		dbList.add("xiaos");
		dbList.add("zhaos");
		dbList.add("wangw");
		DBObject dbObject = new BasicDBObject();
		dbObject.put("people", dbList);
		dbCollection.insert(dbObject);
	}
	//批量插入
	//{name:"qiaoc1",age:1}
	//{name:"qiaoc2",age:2}
	//{name:"qiaoc3",age:3}
	public void batchInsert(){
		DBObject dbObeject1 = new BasicDBObject();
		DBObject dbObeject2 = new BasicDBObject();
		DBObject dbObeject3 = new BasicDBObject();
		dbObeject1.put("name","qiaoc1");
		dbObeject1.put("age",1);
		dbObeject2.put("name","qiaoc2");
		dbObeject2.put("age",2);
		dbObeject3.put("name","qiaoc3");
		dbObeject3.put("age",3);
		
		List<DBObject> dbObjects = new ArrayList<DBObject>();
		dbObjects.add(dbObeject1);
		dbObjects.add(dbObeject2);
		dbObjects.add(dbObeject3);
		//insert方法既可以传入DBObject实现类的对象
		//也可以传入一个ArrayList集合，(批量的插入)
		//方法的重载
		dbCollection.insert(dbObjects);
	}
	
	//db.users.find({name:"qiaoc1"}) 条件查询
	public void query(){
		//查询条件
		DBObject nameObject = new BasicDBObject();
		nameObject.put("name","qiaoc1");
		//执行,DBCursor接受查询后的结果
		DBCursor dbCursor = dbCollection.find(nameObject);
		while(dbCursor.hasNext()){
			System.out.println(dbCursor.next());
		}
	}
	//db.users.find({age:{"$gt":3}})
	public void query2(){
		//{"$gt":3}
		DBObject gtObject = new BasicDBObject();
		gtObject.put("$gt",3);
		//{age:{"$gt":3}}
		DBObject ageObject = new BasicDBObject();
		ageObject.put("age",gtObject);
		//db.users.find()
		DBCursor dbCursor =  dbCollection.find(ageObject);
		//dbCursor.hasNext() 获取指针，若指针下有数据 返回值为true
		while(dbCursor.hasNext()){
			//dbCursor.next()获取当前循环对象
			System.out.println(dbCursor.next());
		}
	}
	//修改
	//db.users.update({name:"qiaoc2"},{$set:{age:3}}, 0 , 1)
	public void update(){
		//第一个参数：查询条件
		DBObject queryObject = new BasicDBObject();
		queryObject.put("name","qiaoc2");
		//第二个参数：需要修改的内容
		DBObject ageObject = new BasicDBObject();
		ageObject.put("age",3);
		DBObject setObject = new BasicDBObject();
		setObject.put("$set",ageObject);
		//执行修改操作
		dbCollection.update(queryObject,setObject, false , true);
	}
	
	//删除方法
	//db.user.remove({name:"qiaoc3"})
	public void remove(){
		//{name:"qiaoc3"}
		DBObject nameObject = new BasicDBObject();
		nameObject.put("name","qiaoc3");
		dbCollection.remove(nameObject);
	}
	
	
}









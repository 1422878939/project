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
	//��ʼ��
	public void init() throws Exception{
		//�������Ӷ���
		mongo = new Mongo("127.0.0.1",27017);
		//��ȡ���ݿ����
		db = mongo.getDB("baizhi");
		//��ȡ�����(Collection)
		dbCollection = MongoDBUtil.getCollection("users");
	}
	//�������
	public void insert(){
		//{name:"JoJo",age:16,salary:600000}
		DBObject dbObject = new BasicDBObject();
		dbObject.put("name","JoJo");
		dbObject.put("age",16);
		dbObject.put("salary","600000");
		dbCollection.insert(dbObject);
	}
	//���뼯��
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
	//��������
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
		//insert�����ȿ��Դ���DBObjectʵ����Ķ���
		//Ҳ���Դ���һ��ArrayList���ϣ�(�����Ĳ���)
		//����������
		dbCollection.insert(dbObjects);
	}
	
	//db.users.find({name:"qiaoc1"}) ������ѯ
	public void query(){
		//��ѯ����
		DBObject nameObject = new BasicDBObject();
		nameObject.put("name","qiaoc1");
		//ִ��,DBCursor���ܲ�ѯ��Ľ��
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
		//dbCursor.hasNext() ��ȡָ�룬��ָ���������� ����ֵΪtrue
		while(dbCursor.hasNext()){
			//dbCursor.next()��ȡ��ǰѭ������
			System.out.println(dbCursor.next());
		}
	}
	//�޸�
	//db.users.update({name:"qiaoc2"},{$set:{age:3}}, 0 , 1)
	public void update(){
		//��һ����������ѯ����
		DBObject queryObject = new BasicDBObject();
		queryObject.put("name","qiaoc2");
		//�ڶ�����������Ҫ�޸ĵ�����
		DBObject ageObject = new BasicDBObject();
		ageObject.put("age",3);
		DBObject setObject = new BasicDBObject();
		setObject.put("$set",ageObject);
		//ִ���޸Ĳ���
		dbCollection.update(queryObject,setObject, false , true);
	}
	
	//ɾ������
	//db.user.remove({name:"qiaoc3"})
	public void remove(){
		//{name:"qiaoc3"}
		DBObject nameObject = new BasicDBObject();
		nameObject.put("name","qiaoc3");
		dbCollection.remove(nameObject);
	}
	
	
}









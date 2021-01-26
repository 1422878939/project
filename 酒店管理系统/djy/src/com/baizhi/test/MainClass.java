package com.baizhi.test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainClass {
	public static void main(String[] args) throws Exception {
	/*	TestMongoAPI mongoAPI = new TestMongoAPI();
		mongoAPI.init();
		mongoAPI.insert();
		mongoAPI.insertList();
		mongoAPI.batchInsert();*/
		Date date = new Date();
		System.out.println(date.toString());
	/*	"2019-07-04 11:13:33"*/
		//转换时间格式的功能类
		//HH 24进制  hh 12进制
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(sdf.format(date));
	}
}






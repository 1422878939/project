package com.baizhi.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.entity.Record;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class QueryAllRecordServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//中文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		
		//username从哪里取？
		//1.user.getUsername()  从session中取
		//2.地址栏拼接
		//选择第一种：登陆标记
		//取出登陆标记
		/*HttpSession session = request.getSession();
		//强转取出User对象
		User user = (User) session.getAttribute("user");
		//获取当前用户的username ->service -> dao ->数据库
		UserService service = new UserServiceImpl();
		ArrayList<Record> recordlist =service.queryAllRecordService(user.getUsername());*/
		UserService service = new UserServiceImpl();
		ArrayList<Record> recordlist =service.queryAllRecordService(username);
		request.setAttribute("recordlist",recordlist);
		request.getRequestDispatcher("/jsp/recordlist.jsp").forward(request, response);
	}
	
}









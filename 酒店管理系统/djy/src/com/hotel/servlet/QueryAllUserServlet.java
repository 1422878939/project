package com.baizhi.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class QueryAllUserServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//中文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//要把dao层从数据库取出的userlist存入request
		UserService service = new UserServiceImpl();
		ArrayList<User> userlist = service.queryAllUserService();
		//得到session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String admin = (String)request.getAttribute("admin");
		//强制登陆
		if(admin != null){
			//以后查询跳转展示数据，建议用request（展示后及时消亡）
			request.setAttribute("userlist", userlist);
			request.setAttribute("admin", "admin");
			request.getRequestDispatcher("/jsp/userlist.jsp").forward(request, response);
		}else 
		if(user == null){
			response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
		}else{
			//以后查询跳转展示数据，建议用request（展示后及时消亡）
			request.setAttribute("userlist", userlist);
			request.getRequestDispatcher("/jsp/userlist.jsp").forward(request, response);
		}
		
		
		
		
	}
	

}









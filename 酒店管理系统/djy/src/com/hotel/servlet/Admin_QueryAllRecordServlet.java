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

public class Admin_QueryAllRecordServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//中文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		
		UserService service = new UserServiceImpl();
		ArrayList<Record> recordlist =service.queryAllRecordService(username);
		request.setAttribute("recordlist",recordlist);
		request.setAttribute("admin", "admin");
		request.getRequestDispatcher("/jsp/recordlist.jsp").forward(request, response);
	}
	
}









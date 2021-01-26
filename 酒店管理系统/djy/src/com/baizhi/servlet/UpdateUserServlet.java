package com.baizhi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class UpdateUserServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserService service = new UserServiceImpl();
		String username = request.getParameter("username");
		String status = request.getParameter("status");
		service.updateUserStatusService(username, status);
		
		request.setAttribute("admin","admin");
		request.getRequestDispatcher("/QueryAllUser").forward(request, response);
		
	}
	
}







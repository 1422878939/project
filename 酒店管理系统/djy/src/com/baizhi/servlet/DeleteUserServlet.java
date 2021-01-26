package com.baizhi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class DeleteUserServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		UserService service = new UserServiceImpl();
		service.deleteUserService(username);
		
		request.setAttribute("admin", "admin");
		request.getRequestDispatcher("/QueryAllUser").forward(request, response);
		
	}
	
}








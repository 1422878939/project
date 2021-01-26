package com.baizhi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置中文正文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//用户退出，销毁session
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
	}
	
}












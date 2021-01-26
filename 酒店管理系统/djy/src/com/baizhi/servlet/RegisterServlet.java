package com.baizhi.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class RegisterServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置中文正文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//接受jsp页面表单传递过来的参数
		// user 7   jsp页面 5
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String systemSource = request.getParameter("systemSource");
		String status = "yes";
		Date date = new Date(); // java.util.Date
		// java.util.Date类型的当前系统时间 ——> String
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String registerDate = sdf.format(date);
		//嵌套service ,把7个参数封装在User传递service
		//封装7个页面接受过来的参数
		User user = new User(username,password,email,phone,status,systemSource,registerDate);
		UserService service = new UserServiceImpl();
		service.insertUserService(user);
		//清空session中的错误信息
		HttpSession session = request.getSession();
		session.removeAttribute("errorMes");
		
		//跳转
		response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
	}
	
}
















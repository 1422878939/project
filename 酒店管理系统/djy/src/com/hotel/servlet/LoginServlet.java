package com.baizhi.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baizhi.dao.UserDao;
import com.baizhi.dao.UserDaoImpl;
import com.baizhi.entity.Record;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.AdminServiceImpl;
import com.baizhi.service.UserService;
import com.baizhi.service.UserServiceImpl;

public class LoginServlet extends HttpServlet{
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置中文正文编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//登陆页面只能接受到 username和password 两个参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		
		if(role.equals("admin")){
			//嵌套service
			AdminService service = new AdminServiceImpl();
			String admin_s = service.adminloginService(username, password);
			//把错误信息存在session中
			HttpSession session = request.getSession();
			if(admin_s.equals("noAdmin")){
				session.setAttribute("errorMes","该管理员不存在");
				response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
			}else if(admin_s.equals("adminErrorPass")){
				session.setAttribute("errorMes", "管理员密码不正确");
				response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
			}else{
				//登陆成功了 
				session.removeAttribute("errorMes");	
				//登陆标记
				session.setAttribute("user", null);
				request.setAttribute("admin","admin");
				
				request.getRequestDispatcher("/QueryAllUser").forward(request, response);
			/*	response.sendRedirect("/rzy_qiaochuang/QueryAllUser");*/
			}
		}
		
		if(!role.equals("admin")){
		
		//嵌套service
		UserService service = new UserServiceImpl();
		String s = service.loginService(username, password);
		//把错误信息存在session中
		HttpSession session = request.getSession();
		if(s.equals("noUser")){
			session.setAttribute("errorMes","该用户不存在");
			response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
		}else if(s.equals("noPass")){
			session.setAttribute("errorMes", "密码不能为空");
			response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
		}else if(s.equals("errorPass")){
			session.setAttribute("errorMes", "密码不正确");
			response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
		}else if(s.equals("errorStatus")){
			session.setAttribute("errorMes","该账号被冻结");
			response.sendRedirect("/rzy_qiaochuang/jsp/login.jsp");
		}else{
			//登陆成功了 （Record）
			session.removeAttribute("errorMes");
			
			/*private String username;
			private String finalip;
			private String finalTime;
			private String systemSource;
			private String registerDate;*/
			//我们已经从jsp页面接受到 username
			UserDao dao = new UserDaoImpl();
			User user =dao.queryUserByUsername(username);
			//登陆标记
			session.setAttribute("user", user);
			//finalip
			String finalip = request.getRemoteAddr();
			//finalTime
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String finalTime = sdf.format(date);
			Record record = new Record(username, finalip , finalTime , user.getSystemSource() , user.getRegisterDate() );
			
			//把记录插入到 数据库中
			dao.insertRecordDao(record);
			response.sendRedirect("/rzy_qiaochuang/QueryAllUser");
			}	
		}
	}
}
				
	


















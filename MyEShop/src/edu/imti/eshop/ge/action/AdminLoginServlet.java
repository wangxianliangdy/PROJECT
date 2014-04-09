package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;
import edu.imti.eshop.ge.entity.Admin;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	     //获得请求消息的内容
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//将请求消息封装成一个admin对象
		Admin admin1 = new Admin();
		admin1.setName(name);
		admin1.setPassword(password);
		
		IAdminDao adminDao = new AdminDaoImpl();
		Admin admin = adminDao.selectAdmin(admin1);
		//测试
		System.out.println(admin);
		
		if(admin == null){
			
			String msg = "您输入的用户名或密码有误，请重新登录！";
			String url = "/MyEShop/index.html";
			String title = "登录页面";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			
//			response.sendRedirect("/MyEShop/index.html");
		//转发到登录界面
		}else{
			//将登录用户的信息保存到会话中存储起来
			HttpSession session = request.getSession();//创建一个会话
			session.setAttribute("ADMIN", admin);//将Admin对象保存到会话中
			
			//登录成功，跳转到后台主界面
            response.sendRedirect("/MyEShop/daemon/main.html");
		
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

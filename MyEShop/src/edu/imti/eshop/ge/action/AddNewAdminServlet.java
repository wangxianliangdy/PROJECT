package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;
import edu.imti.eshop.ge.entity.Admin;

public class AddNewAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得请求信息的内容
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String twoInput = request.getParameter("twoInput");
		
		//判断两次输入的密码是否相同
		if(password.trim().equals(twoInput.trim())){
			//将请求消息内容封装成一admin对象
			Admin admin1 = new Admin();
			admin1.setName(name);
			admin1.setPassword(password);
			admin1.setRole(1);
			
			//调用dao对象
			IAdminDao adminDao = new AdminDaoImpl();
			adminDao.addNewAdmin(admin1);
			
			//添加管理员操作完成之后，跳转
			request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
			
		}else{
			//跳转到提示信息页面
			String msg = "您两次输入的密码不同，请重新填写！";
			String url = "/MyEShop/daemon/addnewadmin.html" ;
			String title = "重新添加";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
		}
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

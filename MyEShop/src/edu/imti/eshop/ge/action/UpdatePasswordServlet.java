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

public class UpdatePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得请求信息
		String str_id = request.getParameter("adminId");
		String name = request.getParameter("name");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Admin adminUpdate=null;
		//new 一个dao对象
		IAdminDao adminDao = new AdminDaoImpl();
		
		//转换数据类型
		Integer adminId = Integer.parseInt(str_id);
		//判断数据库中的密码和用户输入的旧密码是否相同
		Admin adminOld = adminDao.selectOneAdmin(adminId);
		String oldPass = adminOld.getPassword();
		
		//如果数据库中的密码和用户填写的旧密码不同
		if(!oldPass.equals(oldPassword.trim())){
			//跳转到提示信息页面
			String msg = "您输入的旧密码有误，请重新填写！";
			String url = "/MyEShop/daemon/servlet/updatePasswordUIServlet?adminId=" + adminId;
			String title = "重新修改密码";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			
		}else{
			
			//将请求信息封装成一个admin对象
			adminUpdate = new Admin();
			adminUpdate.setAdminId(adminId);
			adminUpdate.setName(name);
			adminUpdate.setPassword(newPassword);
			
			//修改密码
			adminDao.updatePassword(adminUpdate);
			
			//修改密码操作完成之后，跳转
			request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
		}
		
	    
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Admin;
import edu.imti.eshop.ge.entity.Goods;

public class UpdatePasswordUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<HTML>");
		out.println("<head><title> </title><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'><link rel='stylesheet' type='text/css' href='css/right.css'>");
		out.println("</head>");
		out.println("  <BODY>");
		out.println(" <table width='100%' border='0' align='center' cellpadding='0'cellspacing='0'><tr>	" +
				"<td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td><table width='100%' border='0' cellspacing='0'cellpadding='0'><tr><td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>" +
				"<td width='94%' valign='bottom'><span class='STYLE1'> 修改密码</span></td></tr></table></td></tr></table></td></tr></table></td></tr> ");
		  
		  //获得要修改密码的管理员的Id
		  String str_id = request.getParameter("adminId");
		  //数据类型转换
		  Integer adminId = Integer.parseInt(str_id);
		  
		  //调用dao对象
		  IAdminDao adminDao = new AdminDaoImpl();
		  Admin admin = adminDao.selectOneAdmin(adminId);
		
		//form表单
	    out.println(" <tr><td><form action='/MyEShop/daemon/servlet/updatePasswordServlet' method='post'>" +
	    		"<table width='100%' border='0' cellpadding='0' cellspacing='1'bgcolor='#a8c7ce'>" +
	    		"<tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
	    		"<span class='STYLE19'>管理员编号</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
	    		"<input type='text' readonly='readonly'  name='adminId' class='test' value='"+admin.getAdminId()+"'/></span></div></td></tr> " +
	    		"<tr><td height='20' bgcolor='#FFFFFF'>" +
	    		"<div align='center'><span class='STYLE19'>管理员名字</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
	    		"<input type='text' readonly='readonly'  name='name' class='test' value='"+admin.getName()+"'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
				"<span class='STYLE19'>旧密码</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'>" +"<span class='STYLE19'>&nbsp;&nbsp;" +
				"<input type='password' name='oldPassword' class='test'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
				"<span class='STYLE19'>新密码</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
				"<input type='password' name='newPassword' class='test'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'><span class='STYLE19'>&nbsp;</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;" +
				"<input type='submit' value='更新密码' style='border: 1px solid #006699;'/>" +
				"<input type='reset' value='重填信息' style='border: 1px solid #006699;'/></span></div></td></tr></table></form></td></tr></table> ");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

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

public class ShowAllAdminUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
		out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UIF-8' /><title>无标题文档</title><style type='text/css'>");
		out.println("<!--body {	margin-left: 3px;	margin-top: 0px;	margin-right: 3px;	margin-bottom: 0px;}.STYLE1 {	color: #e1e2e3;font-size: 12px;}.STYLE6 {color: #000000; font-size: 12; }.STYLE10 {color: #000000; font-size: 12px; }.STYLE19 {	color: #344b50;	font-size: 12px;}.STYLE21 {	font-size: 12px;	color: #3b6375;}.STYLE22 {	font-size: 12px;	color: #295568;}--></style>");
        out.println("</head>");
        out.println("  <BODY>");
        out.println(" <table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'> <tr><td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>  <tr> <td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>                " +
        		"<td width='94%' valign='bottom'><span class='STYLE1'> 管理员基本信息列表</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <td width='5%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>管理员编号</span></div></td>    " +
        		"    <td width='10%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>管理员名字</span></div></td>    " +
        		"    <td width='10%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>基本操作</span></div></td>  </tr>");
       
        IAdminDao adminDao = new AdminDaoImpl();
        List<Admin> list = adminDao.selectAllAdmin();
        Iterator<Admin> iter = list.iterator();
        
        //获得当前登录管理员的信息
        HttpSession session = request.getSession();
        Admin nowAdmin = (Admin) session.getAttribute("ADMIN");
        
        
        //如果没有管理员信息
        if(list.isEmpty()){
        	out.println("<td height='20' bgcolor='#FFFFFF' colspan='3'><div align='center' colspan='5'> 暂无任何管理员信息....</div></td>");
        }else{
        	//超级管理员登录的情况下
        	if(nowAdmin.getRole()==0){
        		while(iter.hasNext()){
            		Admin admin = iter.next();
            		out.println(" <tr>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+admin.getAdminId()+"</span></div></td>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+admin.getName()+"</span></div></td>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>" );
                    //如果为普通管理员，可以删除
                    if(admin.getRole()!=0){   
                        out.println("<a href='javascript:if(confirm(\"您确定要删除吗？\")) window.location.href=\"/MyEShop/daemon/servlet/deleteAdminServlet?adminId="+admin.getAdminId()+"\"'>删除 </a>");
                    }
                    	out.println("<a href='/MyEShop/daemon/servlet/updatePasswordUIServlet?adminId="+admin.getAdminId()+"'> 修改密码</a>");
                    
                    out.println("</span></div></td></tr>");
            	}
        	}else{
        		//非超级管理员登录的情况下
        		while(iter.hasNext()){
            		Admin admin = iter.next();
            		out.println(" <tr>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+admin.getAdminId()+"</span></div></td>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+admin.getName()+"</span></div></td>");
                    out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>" );
                  //谁登录，谁修改
                    if(nowAdmin.getAdminId() == admin.getAdminId()){
                    	out.println("<a href='/MyEShop/daemon/servlet/updatePasswordUIServlet?adminId="+admin.getAdminId()+"'> 修改密码</a>");
                    }
                    out.println("</span></div></td></tr>");
            	}
        	}
        	
        }
        
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

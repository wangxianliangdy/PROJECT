package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.CustomerDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.entity.Order;

public class DaemonShowMessageUIServlet extends HttpServlet {

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
        
        String title = (String) request.getAttribute("TITLE");
        String url = (String) request.getAttribute("URL");
        String msg = (String) request.getAttribute("MSG");
        
        
        out.println(" <table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'> <tr><td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>  <tr> <td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>                " +
        		"<td width='94%' valign='bottom'><span class='STYLE1'>提示信息</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        
        out.println("<br /><table width='85%' border='0' align='center'><tr>");
		out.println("<td width='61%' colspan='2' align='center'><img src='/MyEShop/front/images/icon_notice.gif' align='absmiddle'/>");
		out.println("<font size='+3'>");
		out.println(msg);
		if(url != null && !"".trim().equals(url)){
			if(url.equals("/MyEShop/index.html")){
				out.println("<br/>点击<a href='" + url + " ' target='_top'>这里</a>返回");
			}else{
				out.println("<br/>点击<a href='" + url + "'>这里</a>返回");
			}
		}
		out.println(title);
		out.println("</font>");
		out.println("</td></tr></table><br /><div class='blank5'></div>");
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

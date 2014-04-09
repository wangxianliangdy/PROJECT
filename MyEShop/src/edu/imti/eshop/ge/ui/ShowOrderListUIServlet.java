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
import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.entity.Goods;
import edu.imti.eshop.ge.entity.Order;

public class ShowOrderListUIServlet extends HttpServlet {

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
        		"<td width='94%' valign='bottom'><span class='STYLE1'> 订单基本信息列表</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <td width='5%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'></span></div></td>    " +
        		"    <td width='15%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>订单流水号</span></div></td>    " +
        		"    <td width='15%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>订单日期</span></div></td>    " +
        		"    <td width='10%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>所属用户</span></div></td>    " +
        		"    <td width='20%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>发货地址</span></div></td>    " +
        		"    <td width='10%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>状态</span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>基本操作</span></div></td>  </tr>");
        //打印订单基本信息
        List<Order> orderList = (List<Order>) request.getAttribute("ORDER");
        
        //new一个顾客dao对象，通过顾客编号查询顾客名字
        ICustomerDao cDao = new CustomerDaoImpl();
        
        if(orderList == null){
        	 IOrderDao orderDao = new OrderDaoImpl();
        	 orderList = orderDao.selectAllOrder();
        }
        Iterator<Order> iter = orderList.iterator();
        //如果没有订单信息
        if(orderList.isEmpty()){
        	out.println("<td height='20' bgcolor='#FFFFFF' colspan='7'><div align='center'> 暂无任何订单信息....</div></td>");
        }else{
        	while(iter.hasNext()){
        		Order order = iter.next();
        		out.println(" <tr>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+order.getId()+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+order.getSerialNum()+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+order.getOrderDate()+"</span></div></td>");
                String customerName = cDao.selectCustomerById(order.getCustomerId());
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customerName+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+order.getAddress()+"</span></div></td>");
                String status = null;
                if(order.getStatus() == 0){
                	status = "等待发货";
                }else{
                	status = "已经发货";
                }
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+status+"</span></div></td>");
                
                out.println("<td height='20' bgcolor='#FFFFFF'><div align='center' class='STYLE21'>" +
                		"<a href='/MyEShop/daemon/servlet/showOrderDetailUIServlet?orderId="+order.getId()+"'>查看</a>" );
               //如果 等待发货 则显示 发货 超链接
                if(order.getStatus()==0){
                	out.println("<a href='/MyEShop/daemon/servlet/updateStatusServlet?orderId="+order.getId()+"'>&nbsp;|&nbsp;发货</a>");
                }
                out.println("</div></td></tr>");
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

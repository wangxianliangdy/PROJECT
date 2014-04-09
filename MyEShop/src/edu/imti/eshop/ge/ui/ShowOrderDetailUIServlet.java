package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.IOrderDetailDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.dao.OrderDetailDaoImpl;
import edu.imti.eshop.ge.entity.Goods;
import edu.imti.eshop.ge.entity.OrderDetail;

public class ShowOrderDetailUIServlet extends HttpServlet {

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
        		"<td width='94%' valign='bottom'><span class='STYLE1'> 商品基本信息列表</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <td width='5%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'> </span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品名称</span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品单价</span></div></td>    " +
        		"    <td width='20%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>数量</span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>小计</span></div></td>  </tr>");
        //获得要查看的订单编号
        String str_id = request.getParameter("orderId");
        //数据类型转换
        Integer orderId = Integer.parseInt(str_id);
        
        //打印订单中商品的信息
        IGoodsDao goodsDao = new GoodsDaoImpl();
        
        //查询订单详情中的信息
        IOrderDetailDao odDao = new OrderDetailDaoImpl();
        List<OrderDetail> list = odDao.selectOrderDetail(orderId);
       
        Iterator<OrderDetail> iter = list.iterator();
        
        //总计
        double allTotal = 0; 
        //如果没有商品信息
        if(list.isEmpty()){
        	out.println("<td height='20' bgcolor='#FFFFFF' colspan='5'><div align='center'> 暂无任何商品信息....</div></td>");
        }else{
        	while(iter.hasNext()){
        		OrderDetail od = iter.next();
        		
        		out.println(" <tr>");
        		//商品编号
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+od.getGoodsId()+"</span></div></td>");
                //商品名称
                Goods goods = goodsDao.selectGoodsById(od.getGoodsId());
                String goodsName = goods.getName();
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+goodsName+"</span></div></td>");
                //商品单价
                Double goodsPrice = goods.getPrice();
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+goodsPrice+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+od.getQuantity()+"</span></div></td>");
                //小计
                Double total = od.getQuantity()*goodsPrice;
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+total+"</span></div></td>");
               
                //计算总计
                allTotal += total;
                
                out.println(" </tr>");
                
        	}
        	    
        	        
            out.println("<tr><td height='20' bgcolor='#FFFFFF' colspan='5'><div align='left'> 总计 ："+allTotal+"</div>" +
            		"<div align='center'><a href='/MyEShop/daemon/servlet/showOrderListUIServlet'>返回订单列表</a></div>" +
            		"</td></tr>");
            
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

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
		out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UIF-8' /><title>�ޱ����ĵ�</title><style type='text/css'>");
		out.println("<!--body {	margin-left: 3px;	margin-top: 0px;	margin-right: 3px;	margin-bottom: 0px;}.STYLE1 {	color: #e1e2e3;font-size: 12px;}.STYLE6 {color: #000000; font-size: 12; }.STYLE10 {color: #000000; font-size: 12px; }.STYLE19 {	color: #344b50;	font-size: 12px;}.STYLE21 {	font-size: 12px;	color: #3b6375;}.STYLE22 {	font-size: 12px;	color: #295568;}--></style>");
        out.println("</head>");
        out.println("  <BODY>");
        out.println(" <table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'> <tr><td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'> <tr> <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>  <tr> <td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>                " +
        		"<td width='94%' valign='bottom'><span class='STYLE1'> ��Ʒ������Ϣ�б�</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <td width='5%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'> </span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>��Ʒ����</span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>��Ʒ����</span></div></td>    " +
        		"    <td width='20%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>����</span></div></td>    " +
        		"    <td width='25%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>С��</span></div></td>  </tr>");
        //���Ҫ�鿴�Ķ������
        String str_id = request.getParameter("orderId");
        //��������ת��
        Integer orderId = Integer.parseInt(str_id);
        
        //��ӡ��������Ʒ����Ϣ
        IGoodsDao goodsDao = new GoodsDaoImpl();
        
        //��ѯ���������е���Ϣ
        IOrderDetailDao odDao = new OrderDetailDaoImpl();
        List<OrderDetail> list = odDao.selectOrderDetail(orderId);
       
        Iterator<OrderDetail> iter = list.iterator();
        
        //�ܼ�
        double allTotal = 0; 
        //���û����Ʒ��Ϣ
        if(list.isEmpty()){
        	out.println("<td height='20' bgcolor='#FFFFFF' colspan='5'><div align='center'> �����κ���Ʒ��Ϣ....</div></td>");
        }else{
        	while(iter.hasNext()){
        		OrderDetail od = iter.next();
        		
        		out.println(" <tr>");
        		//��Ʒ���
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+od.getGoodsId()+"</span></div></td>");
                //��Ʒ����
                Goods goods = goodsDao.selectGoodsById(od.getGoodsId());
                String goodsName = goods.getName();
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+goodsName+"</span></div></td>");
                //��Ʒ����
                Double goodsPrice = goods.getPrice();
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+goodsPrice+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+od.getQuantity()+"</span></div></td>");
                //С��
                Double total = od.getQuantity()*goodsPrice;
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+total+"</span></div></td>");
               
                //�����ܼ�
                allTotal += total;
                
                out.println(" </tr>");
                
        	}
        	    
        	        
            out.println("<tr><td height='20' bgcolor='#FFFFFF' colspan='5'><div align='left'> �ܼ� ��"+allTotal+"</div>" +
            		"<div align='center'><a href='/MyEShop/daemon/servlet/showOrderListUIServlet'>���ض����б�</a></div>" +
            		"</td></tr>");
            
        }
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

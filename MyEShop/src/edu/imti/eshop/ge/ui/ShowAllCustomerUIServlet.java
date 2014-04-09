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

import edu.imti.eshop.ge.dao.CustomerDaoImpl;
import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Admin;
import edu.imti.eshop.ge.entity.Customer;
import edu.imti.eshop.ge.entity.Goods;

public class ShowAllCustomerUIServlet extends HttpServlet {

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
        		"<td width='94%' valign='bottom'><span class='STYLE1'> �˿ͻ�����Ϣ�б�</span></td> </tr> </table></td></tr></table></td></tr></table></td></tr>");
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <td width='18%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>�˿ͱ��</span></div></td>    " +
        		"    <td width='18%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>�˿�����</span></div></td>    " +
        		"    <td width='18%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>�˿�����</span></div></td>    " +
        		"    <td width='18%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>��ϵ����</span></div></td>    " +
        		"    <td width='28%' height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>QQ</span></div></td>  </tr>");
        //��ӡ�˿���Ϣ
        ICustomerDao cDao = new CustomerDaoImpl();
        List<Customer> list = cDao.selectAllCustomer();
        
        //��õ�ǰ��¼����Ա
        HttpSession session = request.getSession();
        Admin nowAdmin = (Admin) session.getAttribute("ADMIN");        
        
        Iterator<Customer> iter = list.iterator();
        //���û����Ʒ��Ϣ
        if(list.isEmpty()){
        	out.println("<td height='20' bgcolor='#FFFFFF' colspan='5'><div align='center'> �����κ���Ʒ��Ϣ....</div></td>");
        }else{
        	while(iter.hasNext()){
        		Customer customer = iter.next();
        		out.println(" <tr>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customer.getId()+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customer.getName()+"</span></div></td>");
                //ֻ�г�������Ա�ſ��Կ������й˿͵�����
                if(nowAdmin.getRole()==0){
                	 out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customer.getPassword()+"</span></div></td>");
                }else{
                	 String msg = "����Ȩ�޲��㣡";
                	 out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+msg+"</span></div></td>");
                }
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customer.getEmail()+"</span></div></td>");
                out.println(" <td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='center'><span class='STYLE19'>"+customer.getQq()+"</span></div></td>");
//                out.println("<td height='20' bgcolor='#FFFFFF'><div align='center' class='STYLE21'>" +
//                		"<a href='/MyEShop/daemon/servlet/showGoodsDetailUIServlet?goodsId="+goods.getGoodsId()+"'>�鿴</a> |  " +
//                		"<a href='javascript:if(confirm(\"��ȷ��Ҫɾ����\")) window.location.href=\"/MyEShop/daemon/servlet/deleteGoodsServlet?goodsId=" + goods.getGoodsId() + "\"'>ɾ��</a> |  " +
//                		"<a href='/MyEShop/daemon/servlet/updateGoodsUIServlet?goodsId="+goods.getGoodsId()+"'>�޸�</a></div></td>");
                out.println(" </tr>");
        	}
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

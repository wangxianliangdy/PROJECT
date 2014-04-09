package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.CustomerDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.entity.Customer;
import edu.imti.eshop.ge.entity.Order;

public class SelectOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/** ��ò�ѯ���ύ����Ϣ  **/
		
		//��ö���ˮ��
		String serialNum = request.getParameter("serialNum");
		
		//����¶����Ĺ˿�����
		String name = request.getParameter("name");
		//���ݹ˿����ֲ�ѯ�˿ͱ��
		Integer customerId = null;
		if(name != null && !"".trim().equals(name)){
			ICustomerDao cDao = new CustomerDaoImpl();
			customerId = cDao.selectCustomerByName(name);
		}
		 
		
		//��ö�����״̬
		String str_status = request.getParameter("status");
		Integer status = null;
		if("".equals(str_status)){
			status = null;
		}
		if(str_status.equals("�ȴ�����")){
			status = 0;
		}
		if(str_status.equals("�Ѿ�����")){
			status = 1;
		}
		
		//����
//		System.out.println(str_status+"-->"+status);
		
		//���ύ����Ϣ��װ��һ��order����
		Order orderCondition = new Order();
		orderCondition.setSerialNum(serialNum);
		orderCondition.setCustomerId(customerId);
		orderCondition.setStatus(status);
		
		IOrderDao orderDao = new OrderDaoImpl();
		List<Order> order = orderDao.selectOrdersByCondition(orderCondition);
		
		//����ѯ������浽������
		request.setAttribute("ORDER", order);
		
		//��ѯ�������֮����ת
		request.getRequestDispatcher("/daemon/servlet/showOrderListUIServlet").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.entity.Order;
//�޸Ķ���״̬(�ȴ�����/�Ѿ�����)��servlet
public class UpdateStatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //����ύ�Ķ������
		String str_orderId = request.getParameter("orderId");
		Integer orderId = Integer.parseInt(str_orderId);
		Integer status = 1;
		//��װ
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(status);
		
		//����dao������޸ķ���
		IOrderDao orderDao = new OrderDaoImpl();
		orderDao.updateOrder(order);
		
		request.getRequestDispatcher("/daemon/servlet/showOrderListUIServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

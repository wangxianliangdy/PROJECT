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
//修改订单状态(等待发货/已经发货)的servlet
public class UpdateStatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //获得提交的订单编号
		String str_orderId = request.getParameter("orderId");
		Integer orderId = Integer.parseInt(str_orderId);
		Integer status = 1;
		//封装
		Order order = new Order();
		order.setId(orderId);
		order.setStatus(status);
		
		//调用dao对象的修改方法
		IOrderDao orderDao = new OrderDaoImpl();
		orderDao.updateOrder(order);
		
		request.getRequestDispatcher("/daemon/servlet/showOrderListUIServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

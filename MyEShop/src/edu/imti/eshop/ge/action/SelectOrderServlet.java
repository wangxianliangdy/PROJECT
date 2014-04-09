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
		/** 获得查询所提交的信息  **/
		
		//获得定流水号
		String serialNum = request.getParameter("serialNum");
		
		//获得下订单的顾客名字
		String name = request.getParameter("name");
		//根据顾客名字查询顾客编号
		Integer customerId = null;
		if(name != null && !"".trim().equals(name)){
			ICustomerDao cDao = new CustomerDaoImpl();
			customerId = cDao.selectCustomerByName(name);
		}
		 
		
		//获得订单的状态
		String str_status = request.getParameter("status");
		Integer status = null;
		if("".equals(str_status)){
			status = null;
		}
		if(str_status.equals("等待发货")){
			status = 0;
		}
		if(str_status.equals("已经发货")){
			status = 1;
		}
		
		//测试
//		System.out.println(str_status+"-->"+status);
		
		//将提交的信息封装成一个order对象
		Order orderCondition = new Order();
		orderCondition.setSerialNum(serialNum);
		orderCondition.setCustomerId(customerId);
		orderCondition.setStatus(status);
		
		IOrderDao orderDao = new OrderDaoImpl();
		List<Order> order = orderDao.selectOrdersByCondition(orderCondition);
		
		//将查询结果保存到容器中
		request.setAttribute("ORDER", order);
		
		//查询操作完成之后，跳转
		request.getRequestDispatcher("/daemon/servlet/showOrderListUIServlet").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

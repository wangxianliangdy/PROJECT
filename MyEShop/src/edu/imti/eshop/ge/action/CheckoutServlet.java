package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.IOrderDao;
import edu.imti.eshop.ge.dao.IOrderDetailDao;
import edu.imti.eshop.ge.dao.OrderDaoImpl;
import edu.imti.eshop.ge.dao.OrderDetailDaoImpl;
import edu.imti.eshop.ge.entity.Cart;
import edu.imti.eshop.ge.entity.CartItem;
import edu.imti.eshop.ge.entity.Order;
import edu.imti.eshop.ge.entity.OrderDetail;
//提交订单的servlet
public class CheckoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得订单的流水号
		String serialNum = request.getParameter("serialNum");
		//获得顾客的编号
		String str_customerId = request.getParameter("customerId");
		//获得发货地址
		String address = request.getParameter("address");
	 
		Integer customerId = Integer.parseInt(str_customerId);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		 Order order = new Order();
		 order.setSerialNum(serialNum);
		 order.setCustomerId(customerId);
		 order.setAddress(address);
		 order.setOrderDate(new Date());
		 order.setStatus(0);
		
		//订单的流水号、顾客的编号、发货地址是保存到订单表tbl_order
		IOrderDao orderDao = new OrderDaoImpl();
		orderDao.addNewOrder(order);
	   
		
		/** 获得订单中商品的信息  **/
		HttpSession session = request.getSession();
		//获得购物车
		Cart cart = (Cart) session.getAttribute("CART");
		
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		
		if(map != null && !map.isEmpty()){
			Iterator<Integer> iter = map.keySet().iterator();
			CartItem item = null;
			
			while(iter.hasNext()){
				item = map.get(iter.next());
				
				//查询订单编号
				Order orderById = orderDao.selectOrderId(serialNum);
				Integer orderId = orderById.getId();
				
			   	//获得商品的编号
				Integer goodsId = item.getGoods().getGoodsId();
				//获得购买商品的数量
				Integer quantity = item.getQuantity();
				//商品的编号和数量是添加到订单详细表tbl_orderdetail
				
				OrderDetail od = new OrderDetail();
				od.setOrderId(orderId);
				od.setGoodsId(goodsId);
				od.setQuantity(quantity);
				
				//new一个OrderDetailDao对象，调用添加订单详情表的方法
				IOrderDetailDao odDao = new OrderDetailDaoImpl();
				odDao.addNewOrderDetail(od);
				
			}
		}
		
		
		//订单提交成功之后，清空购物车
		cart.clearCart();
		
		String msg="谢谢您的惠顾，订单发送成功，欢迎您下次再来！";
		String url = "/MyEShop/front/limit/frontIndexUIServlet";
		String title = "首页"; 
		
		
		request.setAttribute("MSG", msg);
		request.setAttribute("URL", url);
		request.setAttribute("TITLE", title);
		
		//提交订单之后，跳转到前台提示信息页面
		request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

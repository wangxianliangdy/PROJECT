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
//�ύ������servlet
public class CheckoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ö�������ˮ��
		String serialNum = request.getParameter("serialNum");
		//��ù˿͵ı��
		String str_customerId = request.getParameter("customerId");
		//��÷�����ַ
		String address = request.getParameter("address");
	 
		Integer customerId = Integer.parseInt(str_customerId);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		 Order order = new Order();
		 order.setSerialNum(serialNum);
		 order.setCustomerId(customerId);
		 order.setAddress(address);
		 order.setOrderDate(new Date());
		 order.setStatus(0);
		
		//��������ˮ�š��˿͵ı�š�������ַ�Ǳ��浽������tbl_order
		IOrderDao orderDao = new OrderDaoImpl();
		orderDao.addNewOrder(order);
	   
		
		/** ��ö�������Ʒ����Ϣ  **/
		HttpSession session = request.getSession();
		//��ù��ﳵ
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
				
				//��ѯ�������
				Order orderById = orderDao.selectOrderId(serialNum);
				Integer orderId = orderById.getId();
				
			   	//�����Ʒ�ı��
				Integer goodsId = item.getGoods().getGoodsId();
				//��ù�����Ʒ������
				Integer quantity = item.getQuantity();
				//��Ʒ�ı�ź���������ӵ�������ϸ��tbl_orderdetail
				
				OrderDetail od = new OrderDetail();
				od.setOrderId(orderId);
				od.setGoodsId(goodsId);
				od.setQuantity(quantity);
				
				//newһ��OrderDetailDao���󣬵�����Ӷ��������ķ���
				IOrderDetailDao odDao = new OrderDetailDaoImpl();
				odDao.addNewOrderDetail(od);
				
			}
		}
		
		
		//�����ύ�ɹ�֮����չ��ﳵ
		cart.clearCart();
		
		String msg="лл���Ļݹˣ��������ͳɹ�����ӭ���´�������";
		String url = "/MyEShop/front/limit/frontIndexUIServlet";
		String title = "��ҳ"; 
		
		
		request.setAttribute("MSG", msg);
		request.setAttribute("URL", url);
		request.setAttribute("TITLE", title);
		
		//�ύ����֮����ת��ǰ̨��ʾ��Ϣҳ��
		request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

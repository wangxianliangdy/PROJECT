package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.entity.Cart;
import edu.imti.eshop.ge.entity.CartItem;

//���¹��ﳵ����Ʒ������Servlet
public class RenewCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//��ù��ﳵ
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		
		//�������ﳵ������ύ����Ϣ(��Ʒ��ź���Ʒ����)
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		if(map != null && !map.isEmpty()){
			Iterator<Integer> iter = map.keySet().iterator();
			CartItem item = null;
			
			while(iter.hasNext()){
				item = map.get(iter.next());
			    //�����Ʒ���
				Integer goodsId = item.getGoods().getGoodsId();
			    //��ù�����Ʒ������
				String str_quantity = request.getParameter("quantity_"+goodsId);
				
				
				Integer quantity = Integer.parseInt(str_quantity);
				
				//���ù��ﳵ�е�renewCart����
				cart.renewCart(goodsId, quantity);
			
			}
			//�޸Ĳ������֮����ת�����ﳵҳ��
//			request.getRequestDispatcher("/front/limit/showCartUIServlet").forward(request, response);
			
			response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

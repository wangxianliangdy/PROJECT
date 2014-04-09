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

//更新购物车中商品数量的Servlet
public class RenewCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		//获得购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		
		//迭代购物车，获得提交的信息(商品编号和商品数量)
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		if(map != null && !map.isEmpty()){
			Iterator<Integer> iter = map.keySet().iterator();
			CartItem item = null;
			
			while(iter.hasNext()){
				item = map.get(iter.next());
			    //获得商品编号
				Integer goodsId = item.getGoods().getGoodsId();
			    //获得购买商品的数量
				String str_quantity = request.getParameter("quantity_"+goodsId);
				
				
				Integer quantity = Integer.parseInt(str_quantity);
				
				//调用购物车中的renewCart方法
				cart.renewCart(goodsId, quantity);
			
			}
			//修改操作完成之后，跳转到购物车页面
//			request.getRequestDispatcher("/front/limit/showCartUIServlet").forward(request, response);
			
			response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

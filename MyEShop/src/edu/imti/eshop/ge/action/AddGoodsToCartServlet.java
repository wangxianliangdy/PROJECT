package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Cart;

public class AddGoodsToCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得要添加商品的编号
		String str_id = request.getParameter("goodsId");
		//转换数据类型
		Integer goodsId = Integer.parseInt(str_id);
		
		HttpSession session = request.getSession();
		//获得与当前用户关联的购物车
		Cart cart = (Cart) session.getAttribute("CART");
		if(cart == null){
			//如果没有购物车，给当前用户分配一个购物车
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		
		//new一个dao对象
		IGoodsDao goodsDao = new GoodsDaoImpl();
		//往购物车中添加商品
		cart.addGoodsToCart(goodsDao.selectGoodsById(goodsId));
		
		//更新会话中购物车中的内容
		session.setAttribute("CART", cart);
		
		//添加操作完成之后，跳转到购物车页面
		response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.entity.Cart;

public class DeleteGoodsFromCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  //获得要删除商品的编号
		String str_id = request.getParameter("goodsId");
		//字符类型转换
		Integer goodsId = Integer.parseInt(str_id);
		//获得购物车
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		
		//调用购物车类中的移除方法
		cart.removeGoodsById(goodsId);
		
		//移除操作完成之后，跳转
		response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

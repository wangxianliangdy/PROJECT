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
		//���Ҫ�����Ʒ�ı��
		String str_id = request.getParameter("goodsId");
		//ת����������
		Integer goodsId = Integer.parseInt(str_id);
		
		HttpSession session = request.getSession();
		//����뵱ǰ�û������Ĺ��ﳵ
		Cart cart = (Cart) session.getAttribute("CART");
		if(cart == null){
			//���û�й��ﳵ������ǰ�û�����һ�����ﳵ
			cart = new Cart();
			session.setAttribute("CART", cart);
		}
		
		//newһ��dao����
		IGoodsDao goodsDao = new GoodsDaoImpl();
		//�����ﳵ�������Ʒ
		cart.addGoodsToCart(goodsDao.selectGoodsById(goodsId));
		
		//���»Ự�й��ﳵ�е�����
		session.setAttribute("CART", cart);
		
		//��Ӳ������֮����ת�����ﳵҳ��
		response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

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
		  //���Ҫɾ����Ʒ�ı��
		String str_id = request.getParameter("goodsId");
		//�ַ�����ת��
		Integer goodsId = Integer.parseInt(str_id);
		//��ù��ﳵ
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("CART");
		
		//���ù��ﳵ���е��Ƴ�����
		cart.removeGoodsById(goodsId);
		
		//�Ƴ��������֮����ת
		response.sendRedirect("/MyEShop/front/limit/showCartUIServlet");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

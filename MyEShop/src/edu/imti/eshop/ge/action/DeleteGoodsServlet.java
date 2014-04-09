package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Goods;

public class DeleteGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���Ҫɾ����Ʒ�ı��
		String str_id = request.getParameter("goodsId");
		IGoodsDao goodsDao = new GoodsDaoImpl();
		//ת����������
		try{
			Integer goodsId = Integer.parseInt(str_id);
			//����ɾ����Ʒ�ķ���
			goodsDao.deleteGoods(goodsId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//��ѯ������Ʒ��������ĵ�������
		List<Goods> list = goodsDao.selectAllGoods();
		request.setAttribute("LIST", list);
		
		//ɾ���������֮����ת
		response.sendRedirect("/MyEShop/daemon/servlet/showGoodsListUIServlet");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

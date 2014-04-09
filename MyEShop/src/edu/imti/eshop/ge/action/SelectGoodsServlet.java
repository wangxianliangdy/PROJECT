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

public class SelectGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���������Ϣ����
		String  str_id = request.getParameter("goodsId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		Integer goodsId = null;
		if(!"".equals(str_id)){
			//ת����������
	        goodsId = Integer.parseInt(str_id);
		}
		
        //����dao����
		IGoodsDao goodsDao = new GoodsDaoImpl();
		Goods goodsCondition = new Goods();
		goodsCondition.setGoodsId(goodsId);
		goodsCondition.setName(name);
		goodsCondition.setType(type);
		
		//����
		System.out.println("��Ʒ���  "+goodsId);
		System.out.println("��Ʒ����  "+name);
		System.out.println("��Ʒ���  "+type);
		
		List<Goods> list = goodsDao.selectGoodsByCondition(goodsCondition);
		//����Ʒ���϶��󱣴浽������
		request.setAttribute("LIST", list);
		
		//��ѯ�������֮����ת
		request.getRequestDispatcher("/daemon/servlet/showGoodsListUIServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

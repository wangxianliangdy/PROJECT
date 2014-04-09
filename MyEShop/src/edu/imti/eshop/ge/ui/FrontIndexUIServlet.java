package edu.imti.eshop.ge.ui;

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
import edu.imti.eshop.ge.util.PrintUITools;

public class FrontIndexUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		//���ƶ���ҳ��
		PrintUITools.printTopUI(out);
		
		/**  ����������� ---------start----- **/
		out.println("<div class='block clearfix'>");
		out.println("<div class='AreaL'>");
		//������߹������͹��ﳵ����
		PrintUITools.printBulletinAndCart(out, request);
		
		//���������Ʒ������������
		PrintUITools.printGoodsType(out);
		
		out.println("</div>");
		/** �������������� ---------end---------**/
	
		
		
		
		 /** �����ұ���Ʒ����չʾ�� ---------start----- **/
		out.println("<div class='AreaR'>");
		
		String goodsType = request.getParameter("goodsType");
		
		IGoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = null;
		//ת���������ͺͱ��뼯
		if(goodsType != null){
			goodsType = new String(goodsType.getBytes("ISO8859-1"),"UTF-8");
			
			//����
			System.out.println(goodsType);
			
			Goods goodsCondition = new Goods();
			goodsCondition.setGoodsId(null);
			goodsCondition.setName(null);
			goodsCondition.setType(goodsType);
			
			list = goodsDao.selectGoodsByCondition(goodsCondition);
		}else{
			
			list = goodsDao.selectAllGoods();
		}
		
		//������Ʒ��Ϣ
	    PrintUITools.printGoodsShow(out, list);
	
		out.println("</div></div><div class='blank5'></div>");
		
		/**  �����ұ���Ʒ����չʾ�������  ---------end--------- **/
 
		
		
		//���Ƶײ�ҳ��
		PrintUITools.printFootUI(out);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

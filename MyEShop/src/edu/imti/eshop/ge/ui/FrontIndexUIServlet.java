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
		
		//绘制顶层页面
		PrintUITools.printTopUI(out);
		
		/**  绘制左边区域 ---------start----- **/
		out.println("<div class='block clearfix'>");
		out.println("<div class='AreaL'>");
		//绘制左边公告栏和购物车区域
		PrintUITools.printBulletinAndCart(out, request);
		
		//绘制左边商品分类栏的区域
		PrintUITools.printGoodsType(out);
		
		out.println("</div>");
		/** 绘制左边区域结束 ---------end---------**/
	
		
		
		
		 /** 绘制右边商品数据展示区 ---------start----- **/
		out.println("<div class='AreaR'>");
		
		String goodsType = request.getParameter("goodsType");
		
		IGoodsDao goodsDao = new GoodsDaoImpl();
		List<Goods> list = null;
		//转换数据类型和编码集
		if(goodsType != null){
			goodsType = new String(goodsType.getBytes("ISO8859-1"),"UTF-8");
			
			//测试
			System.out.println(goodsType);
			
			Goods goodsCondition = new Goods();
			goodsCondition.setGoodsId(null);
			goodsCondition.setName(null);
			goodsCondition.setType(goodsType);
			
			list = goodsDao.selectGoodsByCondition(goodsCondition);
		}else{
			
			list = goodsDao.selectAllGoods();
		}
		
		//迭代商品信息
	    PrintUITools.printGoodsShow(out, list);
	
		out.println("</div></div><div class='blank5'></div>");
		
		/**  绘制右边商品数据展示区域结束  ---------end--------- **/
 
		
		
		//绘制底层页面
		PrintUITools.printFootUI(out);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

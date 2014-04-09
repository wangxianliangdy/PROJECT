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
		//获得请求消息内容
		String  str_id = request.getParameter("goodsId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		Integer goodsId = null;
		if(!"".equals(str_id)){
			//转换数据类型
	        goodsId = Integer.parseInt(str_id);
		}
		
        //调用dao对象
		IGoodsDao goodsDao = new GoodsDaoImpl();
		Goods goodsCondition = new Goods();
		goodsCondition.setGoodsId(goodsId);
		goodsCondition.setName(name);
		goodsCondition.setType(type);
		
		//测试
		System.out.println("商品编号  "+goodsId);
		System.out.println("商品名称  "+name);
		System.out.println("商品类别  "+type);
		
		List<Goods> list = goodsDao.selectGoodsByCondition(goodsCondition);
		//将商品集合对象保存到容器中
		request.setAttribute("LIST", list);
		
		//查询操作完成之后，跳转
		request.getRequestDispatcher("/daemon/servlet/showGoodsListUIServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

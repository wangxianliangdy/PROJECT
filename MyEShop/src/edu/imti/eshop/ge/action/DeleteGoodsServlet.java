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
		//获得要删除商品的编号
		String str_id = request.getParameter("goodsId");
		IGoodsDao goodsDao = new GoodsDaoImpl();
		//转换数据类型
		try{
			Integer goodsId = Integer.parseInt(str_id);
			//调用删除商品的方法
			goodsDao.deleteGoods(goodsId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//查询所有商品，并保存的到容器中
		List<Goods> list = goodsDao.selectAllGoods();
		request.setAttribute("LIST", list);
		
		//删除操作完成之后，跳转
		response.sendRedirect("/MyEShop/daemon/servlet/showGoodsListUIServlet");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

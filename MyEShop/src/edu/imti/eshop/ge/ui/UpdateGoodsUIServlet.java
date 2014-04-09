package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Goods;

public class UpdateGoodsUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>");
		out.println("<HTML>");
		out.println("<head><title> </title><meta http-equiv='keywords' content='keyword1,keyword2,keyword3'><meta http-equiv='description' content='this is my page'><meta http-equiv='content-type' content='text/html; charset=UTF-8'><link rel='stylesheet' type='text/css' href='css/right.css'>");
		out.println("</head>");
		out.println("  <BODY>");
		out.println(" <table width='100%' border='0' align='center' cellpadding='0'cellspacing='0'><tr>	" +
				"<td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td><table width='100%' border='0' cellspacing='0'cellpadding='0'><tr><td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>" +
				"<td width='94%' valign='bottom'><span class='STYLE1'> 修改商品的基本信息</span></td></tr></table></td></tr></table></td></tr></table></td></tr> ");
		//获得商品的编号
		String str_id = request.getParameter("goodsId");
		
		//数据类型转化
		Integer goodsId = Integer.parseInt(str_id);
		
		//调用dao对象
		IGoodsDao goodsDao = new GoodsDaoImpl();
		Goods goods = goodsDao.selectGoodsById(goodsId);
		
		
		
		//form表单
	    out.println(" <tr><td><form action='/MyEShop/daemon/servlet/updateGoodsServlet' method='post' enctype='multipart/form-data'>" +
	    		"<table width='100%' border='0' cellpadding='0' cellspacing='1'bgcolor='#a8c7ce'><tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
	    		"<span class='STYLE19'>商品编号</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
	    		"<input type='text' readonly='readonly'  name='goodsId' class='test' value='"+goods.getGoodsId()+"'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
				"<span class='STYLE19'>商品名称</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'>" +"<span class='STYLE19'>&nbsp;&nbsp;" +
				"<input type='text' name='name' class='test' value='"+goods.getName()+"'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
				"<span class='STYLE19'>商品类别</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;&nbsp;" +
				"<select name='type' class='STYLE19'>" +
				"<option>"+goods.getType()+"</option>" +
				"<option>书籍</option>" +
				"<option>服装</option>" +
				"<option>数码</option>" +
				"<option>体育用品</option>" +
				"<option>食物</option>" +
				"<option>鞋帽</option>" +
				"<option>宠物</option>" +
				"</select></span></div</td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
				"<span class='STYLE19'>商品单价</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
				"<input type='text' name='price' class='test' value='"+goods.getPrice()+"'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'>" +
		        "<span class='STYLE19'>商品图片</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;&nbsp;" +
		        "<input type='file' name='imgPath' src='/MyEShop/daemon/images/"+goods.getImgPath() +"' class='test'/></span></div></td></tr> ");
		out.println(" <tr><td height='20' bgcolor='#FFFFFF'><div align='center'><span class='STYLE19'>&nbsp;</span></div></td><td height='20' bgcolor='#FFFFFF' class='STYLE6'><div align='left'><span class='STYLE19'>&nbsp;<input type='submit' value='更新商品' style='border: 1px solid #006699;'/><input type='reset' value='重填信息' style='border: 1px solid #006699;'/></span></div></td></tr></table></form></td></tr></table> ");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Goods;
/**
 * 
 *显示商品详情的ServletUI界面
 */
public class ShowGoodsDetailUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        
		//获得商品的编号
		String str_id = request.getParameter("goodsId");
		//数据类型转换
		Integer goodsId = Integer.parseInt(str_id);
		
		//调用dao对象
		IGoodsDao goodsDao = new GoodsDaoImpl();
		Goods goods = goodsDao.selectGoodsById(goodsId);
		//图片路径
		String imgPath = goods.getImgPath();
        if(imgPath == null || imgPath.trim().equals("")){
        	imgPath = "nopic.bmp";
        }
		
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		out.println("<html xmlns='http://www.w3.org/1999/xhtml'>");
		out.println("<head><meta http-equiv='Content-Type' content='text/html; charset=UIF-8' /><title>无标题文档</title><style type='text/css'>");
		out.println("<!--body {	margin-left: 3px;	margin-top: 0px;	margin-right: 3px;	margin-bottom: 0px;}.STYLE1 {	color: #e1e2e3;font-size: 12px;}.STYLE6 {color: #000000; font-size: 12; }.STYLE10 {color: #000000; font-size: 12px; }.STYLE19 {	color: #344b50;	font-size: 12px;}.STYLE21 {	font-size: 12px;	color: #3b6375;}.STYLE22 {	font-size: 12px;	color: #295568;}--></style>");
        out.println("</head>");
        out.println("  <BODY>");
        out.println(" <table width='100%' border='0' align='center' cellpadding='0' cellspacing='0'> " +
        		"<tr><td height='30'><table width='100%' border='0' cellspacing='0' cellpadding='0'> " +
        		"<tr> <td height='24' bgcolor='#353c44'><table width='100%' border='0' cellspacing='0' cellpadding='0'> " +
        		"<tr> <td><table width='100%' border='0' cellspacing='0' cellpadding='0'>  <tr> <td width='6%' height='19' valign='bottom'><div align='center'><img src='images/tb.gif' width='14' height='14' /></div></td>                " +
        		"<td width='94%' valign='bottom'><span class='STYLE1'> 商品详细信息列表</span></td> </tr> " +
        		"</table></td></tr></table></td></tr></table></td></tr>");
        //商品详细信息展示
        out.println(" <tr><td><table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce'>  <tr>   " +
        		"    <tr><td height='30' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品名称</span></div></td> <td>"+goods.getName()+"</td> </tr>   " +
        		"    <tr><td height='30' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品类型</span></div></td> <td>"+goods.getType()+"</td> </tr>  " +
        		"    <tr><td height='30' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品单价</span></div></td> <td>"+goods.getPrice()+"</td> </tr>  " +
        		"    <tr><td height='300' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>商品图片</span></div></td> <td>" +
        		"<img width='280' height='300' src='"+ "/MyEShop/daemon/images/"+imgPath +"'></td> </tr> " +
        		"    <tr colspan='2' size='28'><td height='30' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'><a href='/MyEShop/daemon/servlet/showGoodsListUIServlet'>返回</a></span></div></td>  </tr>");
                
                out.println(" </tr>");
        
        out.println("</table>");
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

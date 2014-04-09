package edu.imti.eshop.ge.util;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Cart;
import edu.imti.eshop.ge.entity.CartItem;
import edu.imti.eshop.ge.entity.Customer;
import edu.imti.eshop.ge.entity.Goods;

//工具类：绘制登录之后的主页面类
public class PrintUITools {
    
	  /** 绘制顶层页面 **/
	public static void printTopUI(PrintWriter out){
		
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		out.println("<html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		out.println("<title>网上购物商城</title><link href='/MyEShop/front/css/style.css' rel='stylesheet' type='text/css' /></head><body>");
		out.println("<div class='block clearfix'>");
		out.println("<div class='f_l'><img src='/MyEShop/front/images/logo.gif'/></div>");
		out.println("<div class='f_r log'>");
		out.println("<ul><li class='userInfo'>");
		out.println("<font id='ECS_MEMBERZONE'><div id='append_parent'></div>");
 		out.println("欢迎光临本店&nbsp;&nbsp;&nbsp;&nbsp;");
 		out.println("<a href='/MyEShop/front/login.html'><img src='/MyEShop/front/images/bnt_log.gif'/></a>");
 		out.println("<a href='/MyEShop/front/register.html'><img src='/MyEShop/front/images/bnt_reg.gif'/></a></font>");
 		out.println("</li></ul></div></div>");
 		out.println("<div  class='blank'></div>");
 		out.println("<div id='mainNav' class='clearfix'>");
 		out.println("<a href='/MyEShop/front/limit/frontIndexUIServlet' class='cur'>首页<span></span></a>");
 		out.println("<a href='###'>买家必看<span></span></a>");
 		out.println("<a href='###'>优惠活动<span></span></a>");
 		out.println("<a href='/MyEShop/front/limit/showCartUIServlet'>查看购物车<span></span></a>");
 		out.println("<a href='###'>报价单<span></span></a>");
 		out.println("<a href='###'>留言板<span></span></a>");
 		out.println("<a href='###'>团购商品<span></span></a>");
 		out.println("</div><div class='block'>");
 		out.println("<div class='box'><div class='helpTitBg clearfix'></div></div>");
 		out.println("</div><div class='blank'></div>");
	}
	
	  /** 绘制底层页面 **/	
	public static void printFootUI(PrintWriter out){
		
		out.println("<div id='bottomNav' class='box'>");
		out.println("<div class='box_1'>");
		out.println("<div class='links clearfix'>");
		//友情链接区域
		for(int i=0;i<5;i++){
			out.println("[<a href='###' target='_blank' title='友情连接'"+(i+1)+">友情连接"+(i+1)+"</a>]");
		}
		out.println("</div></div></div><div class='blank'></div>");
		out.println("<div id='bottomNav' class='box'>");
		out.println("<div class='box_1'>");
		out.println("<div class='bNavList clearfix'>");
		out.println("<div class='f_l'></div>");
		out.println("<div class='f_r'>");
		out.println("<a href='#top'><img src='/MyEShop/front/images/bnt_top.gif' /></a>"); 
		out.println("<a href='###'><img src='/MyEShop/front/images/bnt_home.gif'/></a>");
		out.println("</div></div></div></div><div class='blank'></div>");
		out.println("<div class='text' align='center'>");
		out.println("&copy; 2010-2015 网上商城 版权所有，并保留所有权利。<br />");
		out.println("E-mail: ice.ge@qq.com<br />");
		out.println("ICP备案证书号:<a href='###' target='_blank'>鄂ICP备511688</a><br />");
		out.println("<div align='center'  id='rss'><a href='###'><img src='/MyEShop/front/images/xml_rss2.gif' alt='rss'/></a></div></div>");
		
		out.println("</body></html>");
	}
	
	/** 绘制左边公告栏和购物车区域  **/
	public static void printBulletinAndCart(PrintWriter out,HttpServletRequest request){
		
		HttpSession session=request.getSession();		
		Cart cart = (Cart) session.getAttribute("CART");
		//购物车中商品类型的数量
		int goodsQuantity = 0;
		//购物车中商品的总计
		double goodsTotal = 0;
		
		if(cart != null){
			//获得商品类型的数量
			goodsQuantity = cart.getMap().size();
			//计算购物车中商品的总计
			goodsTotal = cart.getAllTotalPrice();
		}
		
		//左侧公告栏
		out.println("<div class='box'>");
		out.println("<div class='box_1'>");
		out.println("<h3><span>商店公告</span></h3>");
		out.println("<div class='boxCenterList RelaArticle'>");
		out.println("本店现正在进行优惠大酬宾活动，<br/>部分商品数量有限，<br/>先到先得！！！");
		out.println("</div></div></div><div class='blank5'></div>");
		
		//左侧购物车
		out.println("<div class='cart' id='ECS_CARTINFO'>");
		out.println("<a href='/MyEShop/front/limit/showCartUIServlet' title='查看购物车'>" +
				"您的购物车中有 " + goodsQuantity + " 种商品，<br/>总计金额 ￥" + goodsTotal + "元。</a>");
		out.println("</div>");
		out.println("<div class='blank5'></div>");
	}
	
	/** 绘制左边商品分类栏的区域 **/
	public static void printGoodsType(PrintWriter out){
		
		out.println("<div class='box'>");		
		out.println("<div class='box_1'>");
		out.println("<div id='category_tree'>");
		out.println("<dl><dt><a href='/MyEShop/front/limit/frontIndexUIServlet'>所有商品</a></dt></dl>");
		//new一个dao对象，获得保存 所有商品类型 的集合
		IGoodsDao goodsDao = new GoodsDaoImpl();
		List<String> goodsTypes = goodsDao.getAllGoodsTypes();
		Iterator<String> iter = goodsTypes.iterator();
		String goodsType = null;
		while(iter.hasNext()){
			goodsType = iter.next();
			
			out.println("<dl><dt><a href='/MyEShop/front/limit/frontIndexUIServlet?goodsType=" + goodsType + "'>" + goodsType + "</a></dt></dl>");
		}
		
       	out.println("</div></div></div><div class='blank5'></div>");
	}
	
	/** 绘制右边商品数据展示区 **/
	public static void printGoodsShow(PrintWriter out, List<Goods> list){
		
		out.println("<div class='box'>");
		out.println("<div class='box_2 centerPadd'>");
		out.println("<div class='itemTit' id='itemBest'></div>");
		out.println("<div id='show_best_area' class='clearfix goodsBox'>");
		
		Iterator<Goods> iter = list.iterator();
		Goods goods = null;
		while(iter.hasNext()){
			goods = iter.next();
			out.println("<div class='goodsItem'>");
			out.println("<span class='best'></span>");
			//商品图片超链接(点击商品图片，将该商品添加到购物车中)
			out.println("<a href='/MyEShop/front/limit/addGoodsToCartServlet?goodsId=" + goods.getGoodsId() + "'>");
			
			if(goods.getImgPath() == null || goods.getImgPath().trim().equals("")){
				out.println("<img src='/MyEShop/daemon/images/nopic.gif' alt='" + goods.getName() + "' class='goodsimg' /></a>");
			}else{
				out.println("<img src='/MyEShop/daemon/images/" + goods.getImgPath() + "' alt='" + goods.getName() + "' class='goodsimg' /></a>");
			}
			
			out.println("<p><a href='/MyEShop/front/limit/addGoodsToCartServlet?goodsId=" + goods.getGoodsId() + "' title='"+ goods.getName()  + "'>" + goods.getName() + "</a></p>");
			out.println("<font class='f1'>￥" + goods.getPrice() + "元</font>");
			out.println("</div>");
		}
		
		out.println("<div class='more'><a href='###'><img src='/MyEShop/front/images/more.gif' /></a></div>");
		out.println("</div></div></div><div class='blank5'></div>");
		
	}
	
	/**  绘制消息页面的中间显示消息的区域  **/
	public static void printMessage(PrintWriter out,HttpServletRequest request){
		
		String msg = (String) request.getAttribute("MSG");
		String url = (String) request.getAttribute("URL");
		String title  = (String) request.getAttribute("TITLE");
		
		//显示消息的区域
		out.println("<div class='usBox'><div class='usBox_2 clearfix'>");
		out.println("<br /><table width='85%' border='0' align='center'><tr>");
		out.println("<td width='61%' colspan='2' align='center'><img src='/MyEShop/front/images/icon_notice.gif' align='absmiddle'/>");
		out.println("<font size='+3'>");
		out.println(msg);
		out.println("点击<a href='" + url + "'>这里</a>返回" + title + "</font>");
		out.println("</td></tr></table><br /></div></div><div class='blank5'></div>");
		
	}
	
	   /** 绘制购物车数据区  **/
	public static void printCart(PrintWriter out,HttpServletRequest request){
		HttpSession session = request.getSession();
		//获得购物车
		Cart cart = (Cart) session.getAttribute("CART");
		
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		
		out.println("<div class='flowBox'>");
		out.println("<h6><span>商品列表</span></h6>");
		out.println("<form id='formCart' name='formCart' method='post' action='/MyEShop/front/limit/renewCartServlet'>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<th bgcolor='#ffffff'>商品编号</th>");
		out.println("<th bgcolor='#ffffff'>商品名称</th>");
		out.println("<th bgcolor='#ffffff'>本店单价</th>");
		out.println("<th bgcolor='#ffffff'>购买数量</th>");
		out.println("<th bgcolor='#ffffff'>小计</th>");
		out.println("<th bgcolor='#ffffff'>操作</th>");
		out.println("</tr>");
		
		//购物总计 
		double totalPrice = 0;
		
		if(map == null || map.isEmpty()){
			out.println("<tr><td colspan='6' bgcolor='#ffffff' align='center'>您暂时没有购买任何商品......</td></tr>");
		}else{
			Iterator<Integer> iter = map.keySet().iterator();
			CartItem item = null;
			
			while(iter.hasNext()){
				item = map.get(iter.next());
				out.println("<tr>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getGoodsId() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getName() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getPrice() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'><input type='text' name='quantity_" + item.getGoods().getGoodsId() + "' style='border: 1px solid;' value='" + item.getQuantity() + "'/></td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getTotalPrice() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'><a href='javascript:if(confirm(\"您确定要删除吗？\")) window.location.href=\"/MyEShop/front/limit/deleteGoodsFromCartServlet?goodsId=" + item.getGoods().getGoodsId() + "\"'>删除</a></td>");
				out.println("</tr>");
			}
			
			totalPrice = cart.getAllTotalPrice();
			
		}
		
		out.println("</table>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		out.println("购物金额总计 ￥" + totalPrice + "元");
		out.println("</td>");
		out.println("<td align='right' bgcolor='#ffffff'>");
		out.println("<input type='button' value='清空购物车' class='bnt_blue_1' onclick='javascript:window.location.href=\"/MyEShop/front/limit/clearCartServlet\"' />");
		out.println("<input name='update' type='hidden' value='updatecart'/>");
		out.println("<input name='submit' type='submit' class='bnt_blue_1' value='更新购物车' />");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='0' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		//超链接 继续购买
		out.println("<a href='/MyEShop/front/limit/frontIndexUIServlet'><img src='/MyEShop/front/images/continue.gif' alt='continue' /></a>");
		out.println("</td>");
		out.println("<td bgcolor='#ffffff' align='right'>");
		//超链接 结算中心
		out.println("<a href='/MyEShop/front/limit/orderConfirmUIServlet'><img src='/MyEShop/front/images/checkout.gif' alt='checkout' /></a>");
		out.println("</td></tr></table></div><div class='blank5'></div>");
	}
	
	 /**  绘制购物车的订单确认数据区  **/
	public static void printCartOrderConfirm(PrintWriter out,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		//获得购物车
		Cart cart = (Cart) session.getAttribute("CART");
		
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		
		//生成订单流水号(当前时间+顾客编号)
		Customer customer = (Customer) session.getAttribute("CUSTOMER");
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String serialNum = df.format(date) + "_" + customer.getId();
		
		out.println("<div class='flowBox'>");
		out.println("<h6><span>订单流水号:" + serialNum + "</span></h6>");
		out.println("<form  action='/MyEShop/front/limit/checkoutServlet' id='formCart' name='formCart' method='post'>");
		out.println("<input type='hidden' name='serialNum' value='" + serialNum + "' />");
		out.println("<input type='hidden' name='customerId' value='" + customer.getId() + "' />");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<th bgcolor='#ffffff'>商品编号</th>");
		out.println("<th bgcolor='#ffffff'>商品名称</th>");
		out.println("<th bgcolor='#ffffff'>本店单价</th>");
		out.println("<th bgcolor='#ffffff'>购买数量</th>");
		out.println("<th bgcolor='#ffffff'>小计</th>");
		out.println("</tr>");
		
		//购物总计 
		double totalPrice = 0;
		
		if(map == null || map.isEmpty()){
			out.println("<tr><td colspan='5' bgcolor='#ffffff' align='center'>暂时没有购买商品</td></tr>");
		}else{
			Iterator<Integer> iter = map.keySet().iterator();
			CartItem item = null;
			
			while(iter.hasNext()){
				item = map.get(iter.next());
				out.println("<tr>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getGoodsId() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getName() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getGoods().getPrice() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getQuantity() + "</td>");
				out.println("<td bgcolor='#ffffff' align='center'>" + item.getTotalPrice() + "</td>");
				out.println("</tr>");
			}
			
			totalPrice = cart.getAllTotalPrice();
			
		}
		
		out.println("</table>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		out.println("购物金额总计 ￥" + totalPrice + "元");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='0' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		out.println("请填写订单的收货地址：<input name='address' size='50' />&nbsp;&nbsp;&nbsp;&nbsp;<img align='top' src='/MyEShop/front/images/tj.jpg' onclick='javascript:document.forms[\"formCart\"].submit();'/>");
		out.println("</td>");		
		out.println("</tr></table></form></div><div class='blank5'></div>");
		
		
	}
	
}

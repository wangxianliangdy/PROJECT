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

//�����ࣺ���Ƶ�¼֮�����ҳ����
public class PrintUITools {
    
	  /** ���ƶ���ҳ�� **/
	public static void printTopUI(PrintWriter out){
		
		out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		out.println("<html xmlns='http://www.w3.org/1999/xhtml'><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />");
		out.println("<title>���Ϲ����̳�</title><link href='/MyEShop/front/css/style.css' rel='stylesheet' type='text/css' /></head><body>");
		out.println("<div class='block clearfix'>");
		out.println("<div class='f_l'><img src='/MyEShop/front/images/logo.gif'/></div>");
		out.println("<div class='f_r log'>");
		out.println("<ul><li class='userInfo'>");
		out.println("<font id='ECS_MEMBERZONE'><div id='append_parent'></div>");
 		out.println("��ӭ���ٱ���&nbsp;&nbsp;&nbsp;&nbsp;");
 		out.println("<a href='/MyEShop/front/login.html'><img src='/MyEShop/front/images/bnt_log.gif'/></a>");
 		out.println("<a href='/MyEShop/front/register.html'><img src='/MyEShop/front/images/bnt_reg.gif'/></a></font>");
 		out.println("</li></ul></div></div>");
 		out.println("<div  class='blank'></div>");
 		out.println("<div id='mainNav' class='clearfix'>");
 		out.println("<a href='/MyEShop/front/limit/frontIndexUIServlet' class='cur'>��ҳ<span></span></a>");
 		out.println("<a href='###'>��ұؿ�<span></span></a>");
 		out.println("<a href='###'>�Żݻ<span></span></a>");
 		out.println("<a href='/MyEShop/front/limit/showCartUIServlet'>�鿴���ﳵ<span></span></a>");
 		out.println("<a href='###'>���۵�<span></span></a>");
 		out.println("<a href='###'>���԰�<span></span></a>");
 		out.println("<a href='###'>�Ź���Ʒ<span></span></a>");
 		out.println("</div><div class='block'>");
 		out.println("<div class='box'><div class='helpTitBg clearfix'></div></div>");
 		out.println("</div><div class='blank'></div>");
	}
	
	  /** ���Ƶײ�ҳ�� **/	
	public static void printFootUI(PrintWriter out){
		
		out.println("<div id='bottomNav' class='box'>");
		out.println("<div class='box_1'>");
		out.println("<div class='links clearfix'>");
		//������������
		for(int i=0;i<5;i++){
			out.println("[<a href='###' target='_blank' title='��������'"+(i+1)+">��������"+(i+1)+"</a>]");
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
		out.println("&copy; 2010-2015 �����̳� ��Ȩ���У�����������Ȩ����<br />");
		out.println("E-mail: ice.ge@qq.com<br />");
		out.println("ICP����֤���:<a href='###' target='_blank'>��ICP��511688</a><br />");
		out.println("<div align='center'  id='rss'><a href='###'><img src='/MyEShop/front/images/xml_rss2.gif' alt='rss'/></a></div></div>");
		
		out.println("</body></html>");
	}
	
	/** ������߹������͹��ﳵ����  **/
	public static void printBulletinAndCart(PrintWriter out,HttpServletRequest request){
		
		HttpSession session=request.getSession();		
		Cart cart = (Cart) session.getAttribute("CART");
		//���ﳵ����Ʒ���͵�����
		int goodsQuantity = 0;
		//���ﳵ����Ʒ���ܼ�
		double goodsTotal = 0;
		
		if(cart != null){
			//�����Ʒ���͵�����
			goodsQuantity = cart.getMap().size();
			//���㹺�ﳵ����Ʒ���ܼ�
			goodsTotal = cart.getAllTotalPrice();
		}
		
		//��๫����
		out.println("<div class='box'>");
		out.println("<div class='box_1'>");
		out.println("<h3><span>�̵깫��</span></h3>");
		out.println("<div class='boxCenterList RelaArticle'>");
		out.println("���������ڽ����Żݴ������<br/>������Ʒ�������ޣ�<br/>�ȵ��ȵã�����");
		out.println("</div></div></div><div class='blank5'></div>");
		
		//��๺�ﳵ
		out.println("<div class='cart' id='ECS_CARTINFO'>");
		out.println("<a href='/MyEShop/front/limit/showCartUIServlet' title='�鿴���ﳵ'>" +
				"���Ĺ��ﳵ���� " + goodsQuantity + " ����Ʒ��<br/>�ܼƽ�� ��" + goodsTotal + "Ԫ��</a>");
		out.println("</div>");
		out.println("<div class='blank5'></div>");
	}
	
	/** ���������Ʒ������������ **/
	public static void printGoodsType(PrintWriter out){
		
		out.println("<div class='box'>");		
		out.println("<div class='box_1'>");
		out.println("<div id='category_tree'>");
		out.println("<dl><dt><a href='/MyEShop/front/limit/frontIndexUIServlet'>������Ʒ</a></dt></dl>");
		//newһ��dao���󣬻�ñ��� ������Ʒ���� �ļ���
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
	
	/** �����ұ���Ʒ����չʾ�� **/
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
			//��ƷͼƬ������(�����ƷͼƬ��������Ʒ��ӵ����ﳵ��)
			out.println("<a href='/MyEShop/front/limit/addGoodsToCartServlet?goodsId=" + goods.getGoodsId() + "'>");
			
			if(goods.getImgPath() == null || goods.getImgPath().trim().equals("")){
				out.println("<img src='/MyEShop/daemon/images/nopic.gif' alt='" + goods.getName() + "' class='goodsimg' /></a>");
			}else{
				out.println("<img src='/MyEShop/daemon/images/" + goods.getImgPath() + "' alt='" + goods.getName() + "' class='goodsimg' /></a>");
			}
			
			out.println("<p><a href='/MyEShop/front/limit/addGoodsToCartServlet?goodsId=" + goods.getGoodsId() + "' title='"+ goods.getName()  + "'>" + goods.getName() + "</a></p>");
			out.println("<font class='f1'>��" + goods.getPrice() + "Ԫ</font>");
			out.println("</div>");
		}
		
		out.println("<div class='more'><a href='###'><img src='/MyEShop/front/images/more.gif' /></a></div>");
		out.println("</div></div></div><div class='blank5'></div>");
		
	}
	
	/**  ������Ϣҳ����м���ʾ��Ϣ������  **/
	public static void printMessage(PrintWriter out,HttpServletRequest request){
		
		String msg = (String) request.getAttribute("MSG");
		String url = (String) request.getAttribute("URL");
		String title  = (String) request.getAttribute("TITLE");
		
		//��ʾ��Ϣ������
		out.println("<div class='usBox'><div class='usBox_2 clearfix'>");
		out.println("<br /><table width='85%' border='0' align='center'><tr>");
		out.println("<td width='61%' colspan='2' align='center'><img src='/MyEShop/front/images/icon_notice.gif' align='absmiddle'/>");
		out.println("<font size='+3'>");
		out.println(msg);
		out.println("���<a href='" + url + "'>����</a>����" + title + "</font>");
		out.println("</td></tr></table><br /></div></div><div class='blank5'></div>");
		
	}
	
	   /** ���ƹ��ﳵ������  **/
	public static void printCart(PrintWriter out,HttpServletRequest request){
		HttpSession session = request.getSession();
		//��ù��ﳵ
		Cart cart = (Cart) session.getAttribute("CART");
		
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		
		out.println("<div class='flowBox'>");
		out.println("<h6><span>��Ʒ�б�</span></h6>");
		out.println("<form id='formCart' name='formCart' method='post' action='/MyEShop/front/limit/renewCartServlet'>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<th bgcolor='#ffffff'>��Ʒ���</th>");
		out.println("<th bgcolor='#ffffff'>��Ʒ����</th>");
		out.println("<th bgcolor='#ffffff'>���굥��</th>");
		out.println("<th bgcolor='#ffffff'>��������</th>");
		out.println("<th bgcolor='#ffffff'>С��</th>");
		out.println("<th bgcolor='#ffffff'>����</th>");
		out.println("</tr>");
		
		//�����ܼ� 
		double totalPrice = 0;
		
		if(map == null || map.isEmpty()){
			out.println("<tr><td colspan='6' bgcolor='#ffffff' align='center'>����ʱû�й����κ���Ʒ......</td></tr>");
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
				out.println("<td bgcolor='#ffffff' align='center'><a href='javascript:if(confirm(\"��ȷ��Ҫɾ����\")) window.location.href=\"/MyEShop/front/limit/deleteGoodsFromCartServlet?goodsId=" + item.getGoods().getGoodsId() + "\"'>ɾ��</a></td>");
				out.println("</tr>");
			}
			
			totalPrice = cart.getAllTotalPrice();
			
		}
		
		out.println("</table>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		out.println("�������ܼ� ��" + totalPrice + "Ԫ");
		out.println("</td>");
		out.println("<td align='right' bgcolor='#ffffff'>");
		out.println("<input type='button' value='��չ��ﳵ' class='bnt_blue_1' onclick='javascript:window.location.href=\"/MyEShop/front/limit/clearCartServlet\"' />");
		out.println("<input name='update' type='hidden' value='updatecart'/>");
		out.println("<input name='submit' type='submit' class='bnt_blue_1' value='���¹��ﳵ' />");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='0' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		//������ ��������
		out.println("<a href='/MyEShop/front/limit/frontIndexUIServlet'><img src='/MyEShop/front/images/continue.gif' alt='continue' /></a>");
		out.println("</td>");
		out.println("<td bgcolor='#ffffff' align='right'>");
		//������ ��������
		out.println("<a href='/MyEShop/front/limit/orderConfirmUIServlet'><img src='/MyEShop/front/images/checkout.gif' alt='checkout' /></a>");
		out.println("</td></tr></table></div><div class='blank5'></div>");
	}
	
	 /**  ���ƹ��ﳵ�Ķ���ȷ��������  **/
	public static void printCartOrderConfirm(PrintWriter out,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		//��ù��ﳵ
		Cart cart = (Cart) session.getAttribute("CART");
		
		Map<Integer, CartItem> map = null;
		if(cart != null){
			map = cart.getMap();			
		}
		
		//���ɶ�����ˮ��(��ǰʱ��+�˿ͱ��)
		Customer customer = (Customer) session.getAttribute("CUSTOMER");
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String serialNum = df.format(date) + "_" + customer.getId();
		
		out.println("<div class='flowBox'>");
		out.println("<h6><span>������ˮ��:" + serialNum + "</span></h6>");
		out.println("<form  action='/MyEShop/front/limit/checkoutServlet' id='formCart' name='formCart' method='post'>");
		out.println("<input type='hidden' name='serialNum' value='" + serialNum + "' />");
		out.println("<input type='hidden' name='customerId' value='" + customer.getId() + "' />");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='1' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<th bgcolor='#ffffff'>��Ʒ���</th>");
		out.println("<th bgcolor='#ffffff'>��Ʒ����</th>");
		out.println("<th bgcolor='#ffffff'>���굥��</th>");
		out.println("<th bgcolor='#ffffff'>��������</th>");
		out.println("<th bgcolor='#ffffff'>С��</th>");
		out.println("</tr>");
		
		//�����ܼ� 
		double totalPrice = 0;
		
		if(map == null || map.isEmpty()){
			out.println("<tr><td colspan='5' bgcolor='#ffffff' align='center'>��ʱû�й�����Ʒ</td></tr>");
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
		out.println("�������ܼ� ��" + totalPrice + "Ԫ");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<table width='99%' align='center' border='0' cellpadding='5' cellspacing='0' bgcolor='#dddddd'>");
		out.println("<tr>");
		out.println("<td bgcolor='#ffffff'>");
		out.println("����д�������ջ���ַ��<input name='address' size='50' />&nbsp;&nbsp;&nbsp;&nbsp;<img align='top' src='/MyEShop/front/images/tj.jpg' onclick='javascript:document.forms[\"formCart\"].submit();'/>");
		out.println("</td>");		
		out.println("</tr></table></form></div><div class='blank5'></div>");
		
		
	}
	
}

package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.util.PrintUITools;

public class OrderConfirmUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		/**  绘制顶层页面  **/
		PrintUITools.printTopUI(out);
		
		/**  绘制订单页面  **/
		PrintUITools.printCartOrderConfirm(out, request);
		
		/**  绘制底层页面  **/
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

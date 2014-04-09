package edu.imti.eshop.ge.ui;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.util.PrintUITools;

public class ShowCartUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
	     
		//绘制顶部页面
		PrintUITools.printTopUI(out);
		
		//绘制购物车页面
		PrintUITools.printCart(out, request);
		
		
		//绘制底部页面
		PrintUITools.printFootUI(out);
		
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

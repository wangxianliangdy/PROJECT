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
	     
		//���ƶ���ҳ��
		PrintUITools.printTopUI(out);
		
		//���ƹ��ﳵҳ��
		PrintUITools.printCart(out, request);
		
		
		//���Ƶײ�ҳ��
		PrintUITools.printFootUI(out);
		
	
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

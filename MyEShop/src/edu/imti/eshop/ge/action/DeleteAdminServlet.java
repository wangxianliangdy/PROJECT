package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;

public class DeleteAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���Ҫɾ������Ա�ı��
		String str_id = request.getParameter("adminId");
		//ת����������
		Integer adminId = Integer.parseInt(str_id);
		
		//new һ��dao���󣬵���delete����
		IAdminDao adminDao = new AdminDaoImpl();
		adminDao.deleteAdmin(adminId);
		
		//ɾ�����֮����ת
		request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

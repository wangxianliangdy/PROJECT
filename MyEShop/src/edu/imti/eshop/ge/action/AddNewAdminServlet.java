package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;
import edu.imti.eshop.ge.entity.Admin;

public class AddNewAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���������Ϣ������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String twoInput = request.getParameter("twoInput");
		
		//�ж���������������Ƿ���ͬ
		if(password.trim().equals(twoInput.trim())){
			//��������Ϣ���ݷ�װ��һadmin����
			Admin admin1 = new Admin();
			admin1.setName(name);
			admin1.setPassword(password);
			admin1.setRole(1);
			
			//����dao����
			IAdminDao adminDao = new AdminDaoImpl();
			adminDao.addNewAdmin(admin1);
			
			//��ӹ���Ա�������֮����ת
			request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
			
		}else{
			//��ת����ʾ��Ϣҳ��
			String msg = "��������������벻ͬ����������д��";
			String url = "/MyEShop/daemon/addnewadmin.html" ;
			String title = "�������";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
		}
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

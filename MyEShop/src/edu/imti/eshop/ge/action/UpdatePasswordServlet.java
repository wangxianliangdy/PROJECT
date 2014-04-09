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

public class UpdatePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//���������Ϣ
		String str_id = request.getParameter("adminId");
		String name = request.getParameter("name");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		Admin adminUpdate=null;
		//new һ��dao����
		IAdminDao adminDao = new AdminDaoImpl();
		
		//ת����������
		Integer adminId = Integer.parseInt(str_id);
		//�ж����ݿ��е�������û�����ľ������Ƿ���ͬ
		Admin adminOld = adminDao.selectOneAdmin(adminId);
		String oldPass = adminOld.getPassword();
		
		//������ݿ��е�������û���д�ľ����벻ͬ
		if(!oldPass.equals(oldPassword.trim())){
			//��ת����ʾ��Ϣҳ��
			String msg = "������ľ�����������������д��";
			String url = "/MyEShop/daemon/servlet/updatePasswordUIServlet?adminId=" + adminId;
			String title = "�����޸�����";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			
		}else{
			
			//��������Ϣ��װ��һ��admin����
			adminUpdate = new Admin();
			adminUpdate.setAdminId(adminId);
			adminUpdate.setName(name);
			adminUpdate.setPassword(newPassword);
			
			//�޸�����
			adminDao.updatePassword(adminUpdate);
			
			//�޸�����������֮����ת
			request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
		}
		
	    
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

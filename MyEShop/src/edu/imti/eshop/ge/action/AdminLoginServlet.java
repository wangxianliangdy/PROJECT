package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;
import edu.imti.eshop.ge.entity.Admin;

public class AdminLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	     //���������Ϣ������
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//��������Ϣ��װ��һ��admin����
		Admin admin1 = new Admin();
		admin1.setName(name);
		admin1.setPassword(password);
		
		IAdminDao adminDao = new AdminDaoImpl();
		Admin admin = adminDao.selectAdmin(admin1);
		//����
		System.out.println(admin);
		
		if(admin == null){
			
			String msg = "��������û������������������µ�¼��";
			String url = "/MyEShop/index.html";
			String title = "��¼ҳ��";
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			
//			response.sendRedirect("/MyEShop/index.html");
		//ת������¼����
		}else{
			//����¼�û�����Ϣ���浽�Ự�д洢����
			HttpSession session = request.getSession();//����һ���Ự
			session.setAttribute("ADMIN", admin);//��Admin���󱣴浽�Ự��
			
			//��¼�ɹ�����ת����̨������
            response.sendRedirect("/MyEShop/daemon/main.html");
		
		}
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

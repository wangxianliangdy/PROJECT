package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.CustomerDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.entity.Customer;

public class RegisterNewCustomerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����ύ��Ϣ������
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String qq = request.getParameter("qqcode");
		
		//�ж���������������Ƿ���ͬ
//		if(!password.trim().equals(confirm_password.trim())){
//			//����ͬ����ת��ǰ̨��ʾ��Ϣҳ��
//			String msg = "ע��ʧ�ܣ���������������벻��ͬ��";
//			String url = "/MyEShop/front/register.html";
//			String title = "����ע��";
//			request.setAttribute("MSG", msg);
//			request.setAttribute("URL", url);
//			request.setAttribute("TITLE", title);
//			
//			request.getRequestDispatcher("/front/register.html").forward(request, response);
//		}
		
		
		//���ύ��Ϣ��װ��һ��Customer����
		Customer cus = new Customer();
		cus.setName(userName);
		cus.setEmail(email);
		cus.setPassword(password);
		cus.setQq(qq);
		
		//����dao�������ӹ˿͵ķ���
		ICustomerDao cDao = new CustomerDaoImpl();
		cDao.addNewCustomer(cus);
		
		//ע��������֮����ת
		String msg = "��ϲ��ע��ɹ�����Ϊ�����»�Ա��";
		String url = "/MyEShop/front/login.html";
		String title = "��¼ҳ��";
		request.setAttribute("MSG", msg);
		request.setAttribute("URL", url);
		request.setAttribute("TITLE", title);
		
		request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.dao.CustomerDaoImpl;
import edu.imti.eshop.ge.dao.ICustomerDao;
import edu.imti.eshop.ge.entity.Customer;

public class CustomerLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 //����ύ����Ϣ����
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//���ύ�����ݷ�װ��һ��customer����
		Customer cus = new Customer();
		cus.setName(name);
		cus.setPassword(password);
		
		//����dao����Ĳ�ѯ����
		ICustomerDao cDao = new CustomerDaoImpl();
		Customer customer = cDao.selectCustomer(cus);
		
		//����
		System.out.println(customer);
		
		//�ж��Ƿ��¼�ɹ�
		if(customer == null){
			
			//������ʾ��Ϣ������
			String msg = "��������û������������������µ�¼��";
			String url = "/MyEShop/front/login.html";
			String title = "��¼ҳ��";
			
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
		    
		    request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
		}else{
			//�����Ự�������¼�û�����Ϣ
			HttpSession session = request.getSession();
			session.setAttribute("CUSTOMER", customer);
			
			//��¼�ɹ�֮����ת
			//������ʾ��Ϣ������
			String msg = "��ϲ����¼�ɹ���";
			String url = "/MyEShop/front/limit/frontIndexUIServlet";
			String title = "����ҳ��";
			
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
		    request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

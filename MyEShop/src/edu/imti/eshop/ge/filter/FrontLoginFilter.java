package edu.imti.eshop.ge.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.imti.eshop.ge.entity.Admin;
import edu.imti.eshop.ge.entity.Customer;

//ǰ̨�˿͵�¼���ƹ�����
public class FrontLoginFilter implements Filter {

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
        //��ǿ��ת��  
		HttpServletRequest request = (HttpServletRequest) arg0;   
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		//������ʾ��Ϣ������
		String msg = "����δ��¼���̳�";
		String url = "/MyEShop/front/login.html";
		String title = "��¼ҳ��";
		
		
	    
		
		//��ûỰ
		HttpSession session = request.getSession(false);
		if(session == null){
			
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
			
		    request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
			return;
		}
		
		Customer customer =  (Customer) session.getAttribute("CUSTOMER");
		if(customer == null){
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
			
			
			request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
			return;
		}
		
		//������ת������һ����Դ��������/Ŀ����Դ��
		chain.doFilter(arg0, arg1);
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

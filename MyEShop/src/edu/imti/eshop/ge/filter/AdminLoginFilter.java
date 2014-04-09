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

//��̨����Ա��¼���ƹ�����
public class AdminLoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
        //��ǿ��ת��  
		HttpServletRequest request = (HttpServletRequest) arg0;   
		HttpServletResponse response = (HttpServletResponse) arg1;
		
		
		String msg = "����δ��¼��ϵͳ�����ȵ�¼��";
		String url = "/MyEShop/index.html";
		String title = "��¼ҳ��";
		
		
		
		//��ûỰ
		HttpSession session = request.getSession(false);
		if(session == null){
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			return;
		}
		
		Admin admin = (Admin) session.getAttribute("ADMIN");
		if(admin == null){
			request.setAttribute("MSG", msg);
			request.setAttribute("URL", url);
			request.setAttribute("TITLE", title);
			request.getRequestDispatcher("/daemon/admin/daemonShowMessageUIServlet").forward(request, response);
			return;
		}
		
		//������ת������һ����Դ��������/Ŀ����Դ��
		chain.doFilter(arg0, arg1);
	}

	
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

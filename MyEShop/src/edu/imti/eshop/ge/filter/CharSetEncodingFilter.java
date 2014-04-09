package edu.imti.eshop.ge.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharSetEncodingFilter implements Filter {
    
	private String encoding = "UTF-8";
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
         //������Ӧ��Ϣ���ļ����ͺ��ַ�������
		 response.setContentType("text/html;charset=" + encoding);
		 
		 //����������Ϣ���ַ�������
		 request.setCharacterEncoding(encoding);
		 
		 //��ת����һ��������
		 chain.doFilter(request, response);
		 
		
	}

	
	public void init(FilterConfig config) throws ServletException {
        //��øù������ĳ�ʼ���ַ�������   
		String configEncoding = config.getInitParameter("encoding");
        //��������˸ù������ĳ�ʼ���ַ������룬�Ͳ���web.xml�����õ��ַ�������   
		if(configEncoding != null){
        	   encoding = configEncoding;
           }
	}

}

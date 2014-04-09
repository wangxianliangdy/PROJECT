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
         //设置响应消息的文件类型和字符集编码
		 response.setContentType("text/html;charset=" + encoding);
		 
		 //设置请求消息的字符集编码
		 request.setCharacterEncoding(encoding);
		 
		 //跳转到下一个过滤器
		 chain.doFilter(request, response);
		 
		
	}

	
	public void init(FilterConfig config) throws ServletException {
        //获得该过滤器的初始化字符集编码   
		String configEncoding = config.getInitParameter("encoding");
        //如果设置了该过滤器的初始化字符集编码，就采用web.xml中配置的字符集编码   
		if(configEncoding != null){
        	   encoding = configEncoding;
           }
	}

}

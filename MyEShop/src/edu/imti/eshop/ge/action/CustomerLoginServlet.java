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
		 //获得提交的信息内容
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//将提交的内容封装成一个customer对象
		Customer cus = new Customer();
		cus.setName(name);
		cus.setPassword(password);
		
		//调用dao对象的查询方法
		ICustomerDao cDao = new CustomerDaoImpl();
		Customer customer = cDao.selectCustomer(cus);
		
		//测试
		System.out.println(customer);
		
		//判断是否登录成功
		if(customer == null){
			
			//设置提示消息的内容
			String msg = "您输入的用户名或密码有误，请重新登录！";
			String url = "/MyEShop/front/login.html";
			String title = "登录页面";
			
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
		    
		    request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
		}else{
			//创建会话，保存登录用户的信息
			HttpSession session = request.getSession();
			session.setAttribute("CUSTOMER", customer);
			
			//登录成功之后，跳转
			//设置提示消息的内容
			String msg = "恭喜您登录成功！";
			String url = "/MyEShop/front/limit/frontIndexUIServlet";
			String title = "购物页面";
			
			request.setAttribute("MSG", msg);
		    request.setAttribute("URL", url);
		    request.setAttribute("TITLE", title);
		    request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
			
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

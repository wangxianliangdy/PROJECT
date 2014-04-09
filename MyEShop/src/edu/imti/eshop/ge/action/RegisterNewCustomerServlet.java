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
		//获得提交信息的内容
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirm_password = request.getParameter("confirm_password");
		String qq = request.getParameter("qqcode");
		
		//判断两次输入的密码是否相同
//		if(!password.trim().equals(confirm_password.trim())){
//			//不相同，跳转到前台提示信息页面
//			String msg = "注册失败，您两次填入的密码不相同！";
//			String url = "/MyEShop/front/register.html";
//			String title = "重新注册";
//			request.setAttribute("MSG", msg);
//			request.setAttribute("URL", url);
//			request.setAttribute("TITLE", title);
//			
//			request.getRequestDispatcher("/front/register.html").forward(request, response);
//		}
		
		
		//将提交信息封装成一个Customer对象
		Customer cus = new Customer();
		cus.setName(userName);
		cus.setEmail(email);
		cus.setPassword(password);
		cus.setQq(qq);
		
		//调用dao对象的添加顾客的方法
		ICustomerDao cDao = new CustomerDaoImpl();
		cDao.addNewCustomer(cus);
		
		//注册操作完成之后，跳转
		String msg = "恭喜您注册成功，成为本店新会员！";
		String url = "/MyEShop/front/login.html";
		String title = "登录页面";
		request.setAttribute("MSG", msg);
		request.setAttribute("URL", url);
		request.setAttribute("TITLE", title);
		
		request.getRequestDispatcher("/front/login/frontShowMessageUIServlet").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

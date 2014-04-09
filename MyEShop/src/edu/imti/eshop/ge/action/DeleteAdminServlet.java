package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.imti.eshop.ge.dao.AdminDaoImpl;
import edu.imti.eshop.ge.dao.IAdminDao;

public class DeleteAdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获得要删除管理员的编号
		String str_id = request.getParameter("adminId");
		//转换数据类型
		Integer adminId = Integer.parseInt(str_id);
		
		//new 一个dao对象，调用delete方法
		IAdminDao adminDao = new AdminDaoImpl();
		adminDao.deleteAdmin(adminId);
		
		//删除完成之后，跳转
		request.getRequestDispatcher("/daemon/servlet/showAllAdminUIServlet").forward(request, response);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

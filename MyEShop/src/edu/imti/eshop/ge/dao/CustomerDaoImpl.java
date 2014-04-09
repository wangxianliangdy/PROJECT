package edu.imti.eshop.ge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.imti.eshop.ge.entity.Customer;
import edu.imti.eshop.ge.util.JDBCUtil;
//实现与数据库有关操作的方法
public class CustomerDaoImpl implements ICustomerDao {
    /**注册新顾客**/
	public void addNewCustomer(Customer customer) {
         String sql="insert into tbl_customer(name,password,email,qq) values(?,?,?,?)";
	     Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//绑定参数
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPassword());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getQq());
			//执行
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	     
	
	}
    
	/**根据名字和密码查询顾客信息，登录验证时使用**/
	public Customer selectCustomer(Customer customer1) {
		Customer customer = null;
		String sql = "select * from tbl_customer where name=? and password=?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, customer1.getName());
			pst.setString(2, customer1.getPassword());
			
			//执行
			rs = pst.executeQuery();
			//获得结果集
			while(rs.next()){
				//将查询出来的结果封装成一个对象
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setQq(rs.getString("qq"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
		
		return customer;
	}

	/**修改顾客信息，修改密码的时候使用**/
	public void updateCustomer(Customer customer) {
          String sql = "update tbl_customer set name=?,password=?,email=?,qq=? where id=?"; 
	      Connection conn = JDBCUtil.getConnection();
	      PreparedStatement pst = null;
	      try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPassword());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getQq());
			pst.setInt(5, customer.getId());
			//执行
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	}

	
	/**根据顾客编号查询顾客名字**/
	public String selectCustomerById(Integer cId) {
		String customerName = null;
		String sql = "select name from tbl_customer where id=?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setInt(1, cId);
			//执行
			rs = pst.executeQuery();
			//获得结果集
			while(rs.next()){
			    customerName = rs.getString("name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
		
		return customerName;
	}

	
	/** 实现 根据顾客名字查询顾客编号的方法  **/
	public Integer selectCustomerByName(String name) {
		Integer customerId = null;
		String sql = "select id from tbl_customer where name = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, name);
			//执行
			rs = pst.executeQuery();
			//获得结果集
			while(rs.next()){
			    customerId = rs.getInt("id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
		
		
		return customerId;
	}

	/**  实现 查询所有顾客信息的方法  **/
	public List<Customer> selectAllCustomer() {
	  List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from tbl_customer";
	    Connection conn = JDBCUtil.getConnection();
	    Statement sta = null;
	    ResultSet rs = null;
	    try {
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			while(rs.next()){
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setPassword(rs.getString("password"));
				customer.setEmail(rs.getString("email"));
				customer.setQq(rs.getString("qq"));
				
				list.add(customer);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		     JDBCUtil.destroy(conn, sta, rs);
		}
	  
	  
	  
		return list;
	}

	
	
}

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
//ʵ�������ݿ��йز����ķ���
public class CustomerDaoImpl implements ICustomerDao {
    /**ע���¹˿�**/
	public void addNewCustomer(Customer customer) {
         String sql="insert into tbl_customer(name,password,email,qq) values(?,?,?,?)";
	     Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//�󶨲���
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPassword());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getQq());
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	     
	
	}
    
	/**�������ֺ������ѯ�˿���Ϣ����¼��֤ʱʹ��**/
	public Customer selectCustomer(Customer customer1) {
		Customer customer = null;
		String sql = "select * from tbl_customer where name=? and password=?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setString(1, customer1.getName());
			pst.setString(2, customer1.getPassword());
			
			//ִ��
			rs = pst.executeQuery();
			//��ý����
			while(rs.next()){
				//����ѯ�����Ľ����װ��һ������
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

	/**�޸Ĺ˿���Ϣ���޸������ʱ��ʹ��**/
	public void updateCustomer(Customer customer) {
          String sql = "update tbl_customer set name=?,password=?,email=?,qq=? where id=?"; 
	      Connection conn = JDBCUtil.getConnection();
	      PreparedStatement pst = null;
	      try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPassword());
			pst.setString(3, customer.getEmail());
			pst.setString(4, customer.getQq());
			pst.setInt(5, customer.getId());
			//ִ��
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	}

	
	/**���ݹ˿ͱ�Ų�ѯ�˿�����**/
	public String selectCustomerById(Integer cId) {
		String customerName = null;
		String sql = "select name from tbl_customer where id=?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, cId);
			//ִ��
			rs = pst.executeQuery();
			//��ý����
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

	
	/** ʵ�� ���ݹ˿����ֲ�ѯ�˿ͱ�ŵķ���  **/
	public Integer selectCustomerByName(String name) {
		Integer customerId = null;
		String sql = "select id from tbl_customer where name = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setString(1, name);
			//ִ��
			rs = pst.executeQuery();
			//��ý����
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

	/**  ʵ�� ��ѯ���й˿���Ϣ�ķ���  **/
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

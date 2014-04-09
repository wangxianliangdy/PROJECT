package edu.imti.eshop.ge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.imti.eshop.ge.entity.Admin;
import edu.imti.eshop.ge.util.JDBCUtil;
//ʵ�������ݿ��йز����ķ���
public class AdminDaoImpl implements IAdminDao {
		
    /**��ѯ��������Ա��Ϣ���޸�����ʱʹ��**/
	public Admin selectOneAdmin(Integer adminId) {
        Admin admin = null;   
		String sql = "select * from tbl_admin where id=?";
           Connection conn = JDBCUtil.getConnection();
   		   PreparedStatement pst = null;
   		   ResultSet rs = null;
   		   try {
   			   
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, adminId);
			//ִ��
			rs = pst.executeQuery();
			while(rs.next()){
				admin = new Admin();
				admin.setAdminId(adminId);
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
		
		return admin;
	}
   
	
   /**��ѯ����Ա��Ϣ����¼��֤ʱʹ��**/
	public Admin selectAdmin(Admin admin1) {
		Admin admin = null;
		String sql="select * from tbl_admin where name=? and password=?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(sql);
			//�󶨲���
			pst.setString(1, admin1.getName());
			pst.setString(2, admin1.getPassword());
			
			rs = pst.executeQuery();
			//��ý����
			while(rs.next()){
				admin = new Admin();
				admin.setAdminId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setRole(rs.getInt("role"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn,pst, rs);
		}
		
		return admin;
	}


	
	/**  ��ӹ���Ա  **/
	public void addNewAdmin(Admin admin) {
		  String sql = "insert into tbl_admin(name,password,role) values(?,?,?)";
	      Connection conn = JDBCUtil.getConnection();
	      PreparedStatement pst = null;
	      try {
			pst= conn.prepareStatement(sql);
			//�󶨲���
			pst.setString(1, admin.getName());
			pst.setString(2, admin.getPassword());
			pst.setInt(3, admin.getRole());
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	      
	}
	
	/**  ��ѯ���й���Ա��Ϣ  **/
	public List<Admin> selectAllAdmin() {
		List<Admin> list = new ArrayList<Admin>();
		String sql = "select * from tbl_admin";
		Connection conn = JDBCUtil.getConnection();
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			while(rs.next()){
				Admin admin = new Admin();
				admin.setAdminId(rs.getInt("id"));
				admin.setName(rs.getString("name"));
				admin.setPassword(rs.getString("password"));
				admin.setRole(rs.getInt("role"));
				list.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, sta, rs);
		}
		
		return list;
	}

    /*** ʵ�� �޸�����ķ���  **/
	public void updatePassword(Admin admin1) {
		 String sql="update tbl_admin set password=? where id=?";
		 Connection conn = JDBCUtil.getConnection();
	      PreparedStatement pst = null;
	      try {
			pst= conn.prepareStatement(sql);
			//�󶨲���
			pst.setString(1, admin1.getPassword());
			pst.setInt(2, admin1.getAdminId());
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	
	
	}

     /*** ʵ�� ɾ������Ա�ķ���  **/
	public void deleteAdmin(Integer adminId) {
       String sql="delete from tbl_admin where id=?";
       Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
      try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, adminId);
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	}

}

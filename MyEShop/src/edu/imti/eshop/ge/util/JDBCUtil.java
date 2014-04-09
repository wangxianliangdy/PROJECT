package edu.imti.eshop.ge.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/** ���������ݿ������ �� �ͷ�/���������ݿ��йص���Դ  **/
public class JDBCUtil {

      //���������ݿ�����ӵľ�̬����
      public static Connection getConnection(){
    	  Connection conn = null;
    	  DataSource ds = null;
    	  Context initContext = null;
		try {
			//�����������
	//		initContext = new InitialContext();
//			Context envContext  = (Context)initContext.lookup("java:/comp/env");
//		    ds = (DataSource)envContext.lookup("jdbc/MyEShop");
		    //�������Դ����
//		ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/MyEShop");
			//�����������
	//	    conn = ds.getConnection();
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/myshop";
			String user = "root";
			String pwd = "";
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	  
    	  return conn;
      }
      
      

      //�����Ӷ��󷵻ص����ӳ���
      public static void destroy(Connection conn,Statement sta,ResultSet rs){
    	  if(rs != null){
    		  try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
    	  
    	  if(sta != null){
    		  try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
    	  
    	  if(conn != null){
    		  try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	  }
      }
  
}

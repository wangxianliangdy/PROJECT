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

/** 建立与数据库的连接 和 释放/回收与数据库有关的资源  **/
public class JDBCUtil {

      //建立与数据库的连接的静态方法
      public static Connection getConnection(){
    	  Connection conn = null;
    	  DataSource ds = null;
    	  Context initContext = null;
		try {
			//获得容器连接
	//		initContext = new InitialContext();
//			Context envContext  = (Context)initContext.lookup("java:/comp/env");
//		    ds = (DataSource)envContext.lookup("jdbc/MyEShop");
		    //获得数据源连接
//		ds = (DataSource) initContext.lookup("java:/comp/env/jdbc/MyEShop");
			//获得数据连接
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
      
      

      //将连接对象返回到连接池中
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

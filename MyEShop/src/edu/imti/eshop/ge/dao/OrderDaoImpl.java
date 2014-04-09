package edu.imti.eshop.ge.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.imti.eshop.ge.entity.Order;
import edu.imti.eshop.ge.util.JDBCUtil;

public class OrderDaoImpl implements IOrderDao {

	/**  实现添加订单的方法  **/
	public void addNewOrder(Order order) {
          String sql = "insert into tbl_order(serialNum,orderDate,customerId,address,status) values(?,?,?,?,?)";
	      Connection conn = JDBCUtil.getConnection();
	      PreparedStatement pst = null;
	      try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, order.getSerialNum());
			//把java.util.Date改为java.sql.Date
			pst.setDate(2,new java.sql.Date(order.getOrderDate().getTime()));
			pst.setInt(3, order.getCustomerId());
			pst.setString(4, order.getAddress());
			pst.setInt(5, order.getStatus());
			//执行
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	}

	/**实现 查询所有订单的方法 **/
	public List<Order> selectAllOrder() {
        List<Order> orderList = new ArrayList<Order>(); 
        //tbl_order.id,tbl_order.serialNum,tbl_order.orderDate,tbl_customer.id,tbl_order.address,tbl_order.status
		String sql = "select tbl_order.id,tbl_order.serialNum,tbl_order.orderDate,tbl_order.customerId,tbl_order.address,tbl_order.status " +
				"from tbl_order,tbl_customer " +
				"where tbl_customer.id = tbl_order.customerId";
		Connection conn = JDBCUtil.getConnection();
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setSerialNum(rs.getString("serialNum"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setCustomerId(rs.getInt("customerId"));
				order.setAddress(rs.getString("address"));
				order.setStatus(rs.getInt("status"));
				
				orderList.add(order);
				
				//测试
				System.out.println(sql);
				System.out.println(order);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
		      JDBCUtil.destroy(conn, sta, rs);
		}
		
		return orderList;
	     
	}

	/**  实现 修改订单状态的方法  **/
	public void updateOrder(Order order) {
		String sql = "update tbl_order set status=? where id=?";
        Connection conn = JDBCUtil.getConnection();
        PreparedStatement pst = null;
        try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setInt(1, order.getStatus());
			pst.setInt(2, order.getId());
			//执行
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
        
	}

	/** 实现 根据输入的条件(订单流水号/顾客名称/订单状态)查询订单信息  **/
	public List<Order> selectOrdersByCondition(Order orderCondition) {
		List<Order> list = new ArrayList<Order>();
		String sql = "select * from tbl_order where 1=1";
		if(orderCondition != null){
			//如果传了订单流水号
			if(orderCondition.getSerialNum() != null && !orderCondition.getSerialNum().trim().equals("")){
				sql += " and serialNum = ?";
			}
			if(orderCondition.getCustomerId() != null ){
				sql += " and customerId = ?";
			}
			if(orderCondition.getStatus() != null){
				sql += " and status = ?";
			}
		}
		
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(sql);
			int i = 1;
			//动态绑定参数
			if(orderCondition != null){
				//如果传了订单流水号
				if(orderCondition.getSerialNum() != null && !orderCondition.getSerialNum().trim().equals("")){
					pst.setString(i, orderCondition.getSerialNum());
				    i++;
				}
				if(orderCondition.getCustomerId() != null ){
					pst.setInt(i, orderCondition.getCustomerId());
				    i++;
				}
				if(orderCondition.getStatus() != null){
					pst.setInt(i, orderCondition.getStatus());
				}
			}
			
			rs = pst.executeQuery();
			//获得结果集
			while(rs.next()){
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setSerialNum(rs.getString("serialNum"));
				order.setCustomerId(rs.getInt("customerId"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setAddress(rs.getString("address"));
				order.setStatus(rs.getInt("status"));
				
				list.add(order);
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		return list;
	}

	/**  实现 根据订单流水号查询订单编号的方法  **/
	public Order selectOrderId(String serialNum) {
		Order order = null;
		String sql = "select id from tbl_order where serialNum = ?";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, serialNum);
			rs = pst.executeQuery();
			while(rs.next()){
				order = new Order();
				order.setId(rs.getInt("id"));
			  
				//测试
//				System.out.println("orderDaoImpl:"+order.getId());
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return order;
	}

}

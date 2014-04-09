package edu.imti.eshop.ge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.imti.eshop.ge.entity.OrderDetail;
import edu.imti.eshop.ge.util.JDBCUtil;

public class OrderDetailDaoImpl implements IOrderDetailDao {

	//ʵ�� ��Ӷ�����ϸ��Ϣ�ķ���
	public void addNewOrderDetail(OrderDetail od) {
           String sql="insert into tbl_orderdetail(orderId,goodsId,quantity) values(?,?,?)";
	       Connection conn = JDBCUtil.getConnection();
	       PreparedStatement pst = null;
	       try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, od.getOrderId());
			pst.setInt(2, od.getGoodsId());
			pst.setInt(3, od.getQuantity());
			//ִ��
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	
	}

	//ʵ�� ���ݶ�����Ų�ѯ����������ϸ��Ϣ�ķ���
	public List<OrderDetail> selectOrderDetail(Integer orderId) {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		String sql = "select * from tbl_orderdetail where orderId = ? ";
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, orderId);
			
			//��ý����
			rs = pst.executeQuery();
			while(rs.next()){
				OrderDetail od = new OrderDetail();
				od.setId(rs.getInt("id"));
				od.setOrderId(rs.getInt("orderId"));
				od.setGoodsId(rs.getInt("goodsId"));
				od.setQuantity(rs.getInt("quantity"));
				
				list.add(od);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
		
		
		return list;
	}

}

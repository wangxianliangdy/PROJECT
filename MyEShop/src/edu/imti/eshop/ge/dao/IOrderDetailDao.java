package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.OrderDetail;

/**  定义 与‘订单详细表tbl_orderdetail’有关的数据库操作方法  **/
public interface IOrderDetailDao {
     //定义 添加订单详细信息的方法
	public void addNewOrderDetail(OrderDetail od);
	
	//定义 根据订单编号查询单个订单详细信息的方法
	public List<OrderDetail> selectOrderDetail(Integer orderId);
	
	
}

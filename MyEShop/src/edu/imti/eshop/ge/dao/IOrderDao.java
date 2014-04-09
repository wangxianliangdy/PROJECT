package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.Order;
/**  定义 与‘订单表 tbl_order’有关的数据库操作方法  **/
public interface IOrderDao {
    //定义 添加订单的方法
	public void addNewOrder(Order order);
	
	//定义 修改订单的方法
	public void updateOrder(Order order);
	
	//定义 查询所有订单的方法
	public List<Order> selectAllOrder();
	
	//定义 查询所有订单信息的方法
	public List<Order> selectOrdersByCondition(Order orderCondition);
	
	//定义 根据订单流水号查询订单编号的方法
	public Order selectOrderId(String serialNum);
	
	
}

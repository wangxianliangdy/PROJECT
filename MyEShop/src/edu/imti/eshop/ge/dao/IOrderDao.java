package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.Order;
/**  ���� �롮������ tbl_order���йص����ݿ��������  **/
public interface IOrderDao {
    //���� ��Ӷ����ķ���
	public void addNewOrder(Order order);
	
	//���� �޸Ķ����ķ���
	public void updateOrder(Order order);
	
	//���� ��ѯ���ж����ķ���
	public List<Order> selectAllOrder();
	
	//���� ��ѯ���ж�����Ϣ�ķ���
	public List<Order> selectOrdersByCondition(Order orderCondition);
	
	//���� ���ݶ�����ˮ�Ų�ѯ������ŵķ���
	public Order selectOrderId(String serialNum);
	
	
}

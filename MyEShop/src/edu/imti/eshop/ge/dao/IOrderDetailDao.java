package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.OrderDetail;

/**  ���� �롮������ϸ��tbl_orderdetail���йص����ݿ��������  **/
public interface IOrderDetailDao {
     //���� ��Ӷ�����ϸ��Ϣ�ķ���
	public void addNewOrderDetail(OrderDetail od);
	
	//���� ���ݶ�����Ų�ѯ����������ϸ��Ϣ�ķ���
	public List<OrderDetail> selectOrderDetail(Integer orderId);
	
	
}

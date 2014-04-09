package edu.imti.eshop.ge.entity;

import java.util.Date;



//������tbl_order����ʵ����
public class Order {
	//�����ı��
    private Integer id;
    //��������ˮ��
    private String serialNum;
    //�¶���������
    private Date orderDate;
    //�˿͵ı��
    private Integer customerId;
    //������ַ
    private String address;
    //������״̬
    private Integer status;
    
    public Order(){}

	public Order(Integer id, String serialNum, Date orderDate,
			Integer customerId, String address, Integer status) {
		super();
		this.id = id;
		this.serialNum = serialNum;
		this.orderDate = orderDate;
		this.customerId = customerId;
		this.address = address;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
	//����
	@Override
	public String toString() {
		String str = "order-->"+" �������:"+id+" ������ˮ��:"+serialNum+" �˿ͱ��:"+customerId+" ��������:"+orderDate+" ������ַ:"+address+" ����״̬:"+status;
		return str;
	}
    
}

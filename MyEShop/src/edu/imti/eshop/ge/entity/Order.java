package edu.imti.eshop.ge.entity;

import java.util.Date;



//订单（tbl_order）的实体类
public class Order {
	//订单的编号
    private Integer id;
    //订单的流水号
    private String serialNum;
    //下订单的日期
    private Date orderDate;
    //顾客的编号
    private Integer customerId;
    //发货地址
    private String address;
    //订单的状态
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
    
	//测试
	@Override
	public String toString() {
		String str = "order-->"+" 订单编号:"+id+" 订单流水号:"+serialNum+" 顾客编号:"+customerId+" 订单日期:"+orderDate+" 发货地址:"+address+" 订单状态:"+status;
		return str;
	}
    
}

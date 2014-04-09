package edu.imti.eshop.ge.entity;
//���������tbl_orderDetail����ʵ����
public class OrderDetail {
	  //���������ı��
      private Integer id;
      //�������
      private Integer orderId;
      //��Ʒ���
      private Integer goodsId;
      //�������Ʒ������
      private Integer quantity;
      
      public OrderDetail(){}

	public OrderDetail(Integer id, Integer orderId, Integer goodsId,
			Integer quantity) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.goodsId = goodsId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
      
	//����
    @Override
    public String toString() {
    	String str = "orderDetail-->"+"������ϸ����:"+id+" �������:"+orderId+" ��Ʒ���:"+goodsId+" ��Ʒ����:"+quantity;
    	return str;
    }  
}

package edu.imti.eshop.ge.entity;
//订单详情表（tbl_orderDetail）的实体类
public class OrderDetail {
	  //订单详情表的编号
      private Integer id;
      //订单编号
      private Integer orderId;
      //商品编号
      private Integer goodsId;
      //购买的商品的数量
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
      
	//测试
    @Override
    public String toString() {
    	String str = "orderDetail-->"+"订单详细表编号:"+id+" 订单编号:"+orderId+" 商品编号:"+goodsId+" 商品数量:"+quantity;
    	return str;
    }  
}

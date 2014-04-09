package edu.imti.eshop.ge.entity;
//实体类：商品条目项
public class CartItem {
    private Goods goods;
    private Integer quantity;
       
    public CartItem(){
    	
    }
       
	public CartItem(Goods goods, Integer quantity) {
		super();
		this.goods = goods;
		this.quantity = quantity;
	}



	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	};
       
	//计算商品小计
	public double getTotalPrice(){
		return goods.getPrice()*quantity;
	}
       
	//测试
	@Override
	public String toString() {
		String str = "cartItem-->"+"商品:"+goods+" 数量:"+quantity;
		return str;
	}
	
}

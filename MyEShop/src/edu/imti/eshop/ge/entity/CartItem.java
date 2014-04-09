package edu.imti.eshop.ge.entity;
//ʵ���ࣺ��Ʒ��Ŀ��
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
       
	//������ƷС��
	public double getTotalPrice(){
		return goods.getPrice()*quantity;
	}
       
	//����
	@Override
	public String toString() {
		String str = "cartItem-->"+"��Ʒ:"+goods+" ����:"+quantity;
		return str;
	}
	
}

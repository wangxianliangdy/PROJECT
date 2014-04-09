package edu.imti.eshop.ge.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//购物车类
public class Cart {
	//采用键——值对的集合来存放数据
    private Map<Integer, CartItem> map = new HashMap<Integer,CartItem>();

    public Cart(){
    	
    }
    
	public Cart(Map<Integer, CartItem> map) {
		super();
		this.map = map;
	}

	public Map<Integer, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}
    
    //添加商品
    public void addGoodsToCart(Goods goods){
    	//判断购物车中是否存在该商品
    	CartItem item = map.get(goods.getGoodsId());
    	
    	if(item == null){
    		//如果没有该商品类别,创建该类别，并设置数量为1
    		item = new CartItem(goods,1);
    	}else{
    		//如果有该商品类别，则将其数量+1
    		item.setQuantity(item.getQuantity()+1);
    	}
    	
    	//更新购物车中的数据
    	map.put(goods.getGoodsId(), item);
    	
    }
	//更新购物车中的商品数量
    public void renewCart(Integer id,int quantity){
    	//先获得需要更新的商品
    	CartItem item = map.get(id);
    	//更新数量
    	if(quantity >0){
    		item.setQuantity(quantity);
    	}else{
    		map.remove(id);
    	}
    }
    //移除一项购物车中的商品
    public void removeGoodsById(Integer id){
    	map.remove(id);
    }
	
	//清空购物车
    public void clearCart(){
    	map.clear();
    }
	
	//总计
    public double getAllTotalPrice(){
        double result = 0;
		Iterator<Integer> iter = map.keySet().iterator();
		while(iter.hasNext()){
			Integer id = iter.next();
			CartItem item = map.get(id);
			result += item.getTotalPrice();
		}
		
		return result;
    }
}

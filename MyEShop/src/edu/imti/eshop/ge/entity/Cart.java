package edu.imti.eshop.ge.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//���ﳵ��
public class Cart {
	//���ü�����ֵ�Եļ������������
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
    
    //�����Ʒ
    public void addGoodsToCart(Goods goods){
    	//�жϹ��ﳵ���Ƿ���ڸ���Ʒ
    	CartItem item = map.get(goods.getGoodsId());
    	
    	if(item == null){
    		//���û�и���Ʒ���,��������𣬲���������Ϊ1
    		item = new CartItem(goods,1);
    	}else{
    		//����и���Ʒ�����������+1
    		item.setQuantity(item.getQuantity()+1);
    	}
    	
    	//���¹��ﳵ�е�����
    	map.put(goods.getGoodsId(), item);
    	
    }
	//���¹��ﳵ�е���Ʒ����
    public void renewCart(Integer id,int quantity){
    	//�Ȼ����Ҫ���µ���Ʒ
    	CartItem item = map.get(id);
    	//��������
    	if(quantity >0){
    		item.setQuantity(quantity);
    	}else{
    		map.remove(id);
    	}
    }
    //�Ƴ�һ��ﳵ�е���Ʒ
    public void removeGoodsById(Integer id){
    	map.remove(id);
    }
	
	//��չ��ﳵ
    public void clearCart(){
    	map.clear();
    }
	
	//�ܼ�
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

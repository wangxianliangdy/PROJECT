package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.Goods;
/**  定义 与‘商品表 tbl_goods’有关的数据库操作方法  **/
public interface IGoodsDao {
	
	//定义 添加商品信息的方法
	public void addNewGoods(Goods goods);
	
	//定义 删除某个商品信息的方法
	public void deleteGoods(Integer goodsId);
	
	//定义 修改某个商品信息的方法
	public void updateGoods(Goods goods);
	
	//定义 通过某些条件查询商品信息的方法(编号/名称/类别) 
	public List<Goods> selectGoodsByCondition(Goods goodsCondition);
	
	//定义 查询所有商品信息的方法
	public List<Goods> selectAllGoods();
	
	//定义 通过商品编号查询单个商品信息的方法
	public Goods selectGoodsById(Integer goodsId);
	
	//定义 查询所有商品类型的方法
	public List<String> getAllGoodsTypes();
}

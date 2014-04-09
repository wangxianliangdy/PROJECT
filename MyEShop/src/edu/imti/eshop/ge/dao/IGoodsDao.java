package edu.imti.eshop.ge.dao;

import java.util.List;

import edu.imti.eshop.ge.entity.Goods;
/**  ���� �롮��Ʒ�� tbl_goods���йص����ݿ��������  **/
public interface IGoodsDao {
	
	//���� �����Ʒ��Ϣ�ķ���
	public void addNewGoods(Goods goods);
	
	//���� ɾ��ĳ����Ʒ��Ϣ�ķ���
	public void deleteGoods(Integer goodsId);
	
	//���� �޸�ĳ����Ʒ��Ϣ�ķ���
	public void updateGoods(Goods goods);
	
	//���� ͨ��ĳЩ������ѯ��Ʒ��Ϣ�ķ���(���/����/���) 
	public List<Goods> selectGoodsByCondition(Goods goodsCondition);
	
	//���� ��ѯ������Ʒ��Ϣ�ķ���
	public List<Goods> selectAllGoods();
	
	//���� ͨ����Ʒ��Ų�ѯ������Ʒ��Ϣ�ķ���
	public Goods selectGoodsById(Integer goodsId);
	
	//���� ��ѯ������Ʒ���͵ķ���
	public List<String> getAllGoodsTypes();
}

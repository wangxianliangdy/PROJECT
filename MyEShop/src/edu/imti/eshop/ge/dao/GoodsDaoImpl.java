package edu.imti.eshop.ge.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.imti.eshop.ge.entity.Goods;
import edu.imti.eshop.ge.util.JDBCUtil;
//ʵ�������ݿ��йز����ķ���
public class GoodsDaoImpl implements IGoodsDao {
    /** �������Ʒ **/
	public void addNewGoods(Goods goods) {
         String sql = "insert into tbl_goods(name,type,price,imgPath) values(?,?,?,?)";
	     Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setString(1, goods.getName());
			pst.setString(2, goods.getType());
			pst.setDouble(3, goods.getPrice());
			pst.setString(4, goods.getImgPath());
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	}
    
	/**  ������Ʒ���ɾ��һ����Ʒ  **/
	public void deleteGoods(Integer goodsId) {
		String sql = "delete from tbl_goods where id=?"; 
		Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
        try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, goodsId);
			//ִ��
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	}
    
	/** ��ѯ������Ʒ����Ϣ **/
	public List<Goods> selectAllGoods() {
		List<Goods> list = new ArrayList<Goods>(); 
		String sql = "select * from tbl_goods";
		Connection conn = JDBCUtil.getConnection();
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			rs = sta.executeQuery(sql);
			while(rs.next()){
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setType(rs.getString("type"));
				goods.setPrice(rs.getDouble("price"));
				goods.setImgPath(rs.getString("imgPath"));
				//����Ʒ������ӵ�������
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
		return list;
	}

	/** ���ݹ˿�����Ĳ�ѯ���� ��ѯ����Ӧ����Ʒ����Ϣ **/
	public List<Goods> selectGoodsByCondition(Goods goodsCondition) {
		List<Goods> list = new ArrayList<Goods>();
		String sql = "select * from tbl_goods where 1=1"; 
		if(goodsCondition != null){
		    //���������Ʒ���id
			if(goodsCondition.getGoodsId() != null && !goodsCondition.getGoodsId().equals("")){
				sql += " and id = ?";
			}
			//���������Ʒ����name
			if(goodsCondition.getName() != null && !goodsCondition.getName().trim().equals("")){
				sql += " and name like ?";
			}
			//���ѡ������Ʒ���type
			if(goodsCondition.getType() != null ){
				sql += " and type like ?";
			}
		}
		 Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     ResultSet rs = null;
	     try {
			pst = conn.prepareStatement(sql);
			int i = 1;
			if(goodsCondition != null){
				//���������Ʒ���id
				if(goodsCondition.getGoodsId() != null && !goodsCondition.getGoodsId().equals("")){
					pst.setInt(i, goodsCondition.getGoodsId());
					i++;
				}
				//���������Ʒ����name
				if(goodsCondition.getName() != null && !goodsCondition.getName().trim().equals("")){
					pst.setString(i, "%"+goodsCondition.getName()+"%");
				      i++;
				}
				//���ѡ������Ʒ���type
				if(goodsCondition.getType() != null ){
					pst.setString(i, "%"+goodsCondition.getType()+"%");
				    
				}
				
				System.out.println(sql);
				rs = pst.executeQuery();
				while(rs.next()){
					Goods goods = new Goods();
					goods.setGoodsId(rs.getInt("id"));
					goods.setName(rs.getString("name"));
					goods.setType(rs.getString("type"));
					goods.setPrice(rs.getDouble("price"));
					goods.setImgPath(rs.getString("imgPath"));
					//����Ʒ������ӵ�������
					list.add(goods);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
		
		return list;
	}

	/**  ������Ʒ��Ų�ѯһ����Ʒ ����> ��ʾ��Ҫ�޸ĵ���Ʒ��Ϣʱʹ��  **/
	public Goods selectGoodsById(Integer goodsId) {
		Goods goods = null;
		String sql="select * from tbl_goods where id=?";
		Connection conn = JDBCUtil.getConnection();
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setInt(1, goodsId);
			//ִ��
			rs = pst.executeQuery();
			while(rs.next()){
				goods = new Goods();
				goods.setGoodsId(rs.getInt("id"));
				goods.setName(rs.getString("name"));
				goods.setType(rs.getString("type"));
				goods.setPrice(rs.getDouble("price"));
				goods.setImgPath(rs.getString("imgPath"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, rs);
		}
	     
		return goods;
	}

	/**  �޸���Ʒ��Ϣ  **/
	public void updateGoods(Goods goods) {
		String sql = "update tbl_goods set name=?,type=?,price=?,imgPath=? where id=?";
		Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//��̬�󶨲���
			pst.setString(1, goods.getName());
			pst.setString(2, goods.getType());
			pst.setDouble(3, goods.getPrice());
			pst.setString(4, goods.getImgPath());
			pst.setInt(5, goods.getGoodsId());
			//ִ��
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}

	}

	/**ʵ�� ��ѯ������Ʒ���͵ķ���**/
	public List<String> getAllGoodsTypes() {
		List<String> list = new ArrayList<String>();
		String sql = "select distinct type from tbl_goods";
		Connection conn = JDBCUtil.getConnection();
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			//��ý����
			rs = sta.executeQuery(sql);
			while(rs.next()){
				String s = rs.getString("type");
			    
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, sta, rs);
		}
		
		return list;
	}

}

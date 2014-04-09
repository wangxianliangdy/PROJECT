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
//实现与数据库有关操作的方法
public class GoodsDaoImpl implements IGoodsDao {
    /** 添加新商品 **/
	public void addNewGoods(Goods goods) {
         String sql = "insert into tbl_goods(name,type,price,imgPath) values(?,?,?,?)";
	     Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, goods.getName());
			pst.setString(2, goods.getType());
			pst.setDouble(3, goods.getPrice());
			pst.setString(4, goods.getImgPath());
			//执行
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	
	}
    
	/**  根据商品编号删除一项商品  **/
	public void deleteGoods(Integer goodsId) {
		String sql = "delete from tbl_goods where id=?"; 
		Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
        try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setInt(1, goodsId);
			//执行
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}
	}
    
	/** 查询所有商品的信息 **/
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
				//将商品对象添加到集合中
				list.add(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
		return list;
	}

	/** 根据顾客输入的查询条件 查询所对应的商品的信息 **/
	public List<Goods> selectGoodsByCondition(Goods goodsCondition) {
		List<Goods> list = new ArrayList<Goods>();
		String sql = "select * from tbl_goods where 1=1"; 
		if(goodsCondition != null){
		    //如果传了商品编号id
			if(goodsCondition.getGoodsId() != null && !goodsCondition.getGoodsId().equals("")){
				sql += " and id = ?";
			}
			//如果传了商品名称name
			if(goodsCondition.getName() != null && !goodsCondition.getName().trim().equals("")){
				sql += " and name like ?";
			}
			//如果选择了商品类别type
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
				//如果传了商品编号id
				if(goodsCondition.getGoodsId() != null && !goodsCondition.getGoodsId().equals("")){
					pst.setInt(i, goodsCondition.getGoodsId());
					i++;
				}
				//如果传了商品名称name
				if(goodsCondition.getName() != null && !goodsCondition.getName().trim().equals("")){
					pst.setString(i, "%"+goodsCondition.getName()+"%");
				      i++;
				}
				//如果选择了商品类别type
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
					//将商品对象添加到集合中
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

	/**  根据商品编号查询一项商品 ——> 显示所要修改的商品信息时使用  **/
	public Goods selectGoodsById(Integer goodsId) {
		Goods goods = null;
		String sql="select * from tbl_goods where id=?";
		Connection conn = JDBCUtil.getConnection();
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setInt(1, goodsId);
			//执行
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

	/**  修改商品信息  **/
	public void updateGoods(Goods goods) {
		String sql = "update tbl_goods set name=?,type=?,price=?,imgPath=? where id=?";
		Connection conn = JDBCUtil.getConnection();
	     PreparedStatement pst = null;
	     try {
			pst = conn.prepareStatement(sql);
			//动态绑定参数
			pst.setString(1, goods.getName());
			pst.setString(2, goods.getType());
			pst.setDouble(3, goods.getPrice());
			pst.setString(4, goods.getImgPath());
			pst.setInt(5, goods.getGoodsId());
			//执行
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.destroy(conn, pst, null);
		}

	}

	/**实现 查询所有商品类型的方法**/
	public List<String> getAllGoodsTypes() {
		List<String> list = new ArrayList<String>();
		String sql = "select distinct type from tbl_goods";
		Connection conn = JDBCUtil.getConnection();
		Statement sta = null;
		ResultSet rs = null;
		try {
			sta = conn.createStatement();
			//获得结果集
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

package edu.imti.eshop.ge.dao;


import java.util.List;

import edu.imti.eshop.ge.entity.Admin;
/**  定义 与‘管理员表tbl_admin’有关的数据库操作方法  **/
public interface IAdminDao {
    //定义 查询单个管理员的方法，登录时使用
	public Admin selectAdmin(Admin admin1);
	
	//定义 查询所有管理员信息的方法
	public List<Admin> selectAllAdmin();
	
	//定义 添加管理员的方法
	public void addNewAdmin(Admin admin);
	
	//定义 修改管理员密码时使用的方法
	public Admin selectOneAdmin(Integer adminId);
	
	//定义 修改密码的方法
	public void updatePassword(Admin admin1);
	
	//定义 删除管理员的方法
	public void deleteAdmin(Integer adminId);
	
}

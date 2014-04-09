package edu.imti.eshop.ge.dao;
/**  定义 与‘顾客表 tbl_customer’有关的数据库操作方法  **/
import java.util.List;

import edu.imti.eshop.ge.entity.Customer;

public interface ICustomerDao {
    //定义 查询顾客信息的方法
	public Customer selectCustomer(Customer customer1);

	//定义 修改顾客信息的方法
	public void updateCustomer(Customer customer);

    //定义 注册(添加)顾客信息的方法
	public void addNewCustomer(Customer customer);

    //定义 根据顾客编号查询顾客信息的方法
	public String selectCustomerById(Integer cId);
	
	 //定义 根据顾客名字查询顾客编号的方法
	public Integer selectCustomerByName(String cName);

	// 定义 查询所有顾客信息的方法
	public List<Customer> selectAllCustomer();
}

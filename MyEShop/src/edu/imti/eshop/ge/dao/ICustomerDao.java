package edu.imti.eshop.ge.dao;
/**  ���� �롮�˿ͱ� tbl_customer���йص����ݿ��������  **/
import java.util.List;

import edu.imti.eshop.ge.entity.Customer;

public interface ICustomerDao {
    //���� ��ѯ�˿���Ϣ�ķ���
	public Customer selectCustomer(Customer customer1);

	//���� �޸Ĺ˿���Ϣ�ķ���
	public void updateCustomer(Customer customer);

    //���� ע��(���)�˿���Ϣ�ķ���
	public void addNewCustomer(Customer customer);

    //���� ���ݹ˿ͱ�Ų�ѯ�˿���Ϣ�ķ���
	public String selectCustomerById(Integer cId);
	
	 //���� ���ݹ˿����ֲ�ѯ�˿ͱ�ŵķ���
	public Integer selectCustomerByName(String cName);

	// ���� ��ѯ���й˿���Ϣ�ķ���
	public List<Customer> selectAllCustomer();
}

package edu.imti.eshop.ge.dao;


import java.util.List;

import edu.imti.eshop.ge.entity.Admin;
/**  ���� �롮����Ա��tbl_admin���йص����ݿ��������  **/
public interface IAdminDao {
    //���� ��ѯ��������Ա�ķ�������¼ʱʹ��
	public Admin selectAdmin(Admin admin1);
	
	//���� ��ѯ���й���Ա��Ϣ�ķ���
	public List<Admin> selectAllAdmin();
	
	//���� ��ӹ���Ա�ķ���
	public void addNewAdmin(Admin admin);
	
	//���� �޸Ĺ���Ա����ʱʹ�õķ���
	public Admin selectOneAdmin(Integer adminId);
	
	//���� �޸�����ķ���
	public void updatePassword(Admin admin1);
	
	//���� ɾ������Ա�ķ���
	public void deleteAdmin(Integer adminId);
	
}

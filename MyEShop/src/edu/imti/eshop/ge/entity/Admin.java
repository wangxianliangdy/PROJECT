package edu.imti.eshop.ge.entity;
/**
 * ��̨����Ա��tbl_admin����ʵ����
 * */
public class Admin {
	 //����Ա�ı��
     private Integer adminId;
     //����Ա������
     private String name;
     //����Ա������
     private String password;
     //����Ա�Ľ�ɫ
     private Integer role;
     
     public Admin(){  }

	
     public Admin(Integer adminId, String name, String password, Integer role) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.password = password;
		this.role = role;
	}


	public Integer getAdminId() {
		return adminId;
	}


	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getRole() {
		return role;
	}


	public void setRole(Integer role) {
		this.role = role;
	}


	//����
     @Override
    public String toString() {
    	return "admin-->"+"���:"+ adminId + " ����:"+name+" ����:"+password+" ��ɫ:"+role;
    }
}

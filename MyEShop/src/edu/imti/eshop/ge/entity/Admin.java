package edu.imti.eshop.ge.entity;
/**
 * 后台管理员（tbl_admin）的实体类
 * */
public class Admin {
	 //管理员的编号
     private Integer adminId;
     //管理员的名字
     private String name;
     //管理员的密码
     private String password;
     //管理员的角色
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


	//测试
     @Override
    public String toString() {
    	return "admin-->"+"编号:"+ adminId + " 名字:"+name+" 密码:"+password+" 角色:"+role;
    }
}

package edu.imti.eshop.ge.entity;
//ǰ̨�˿ͣ�customer����ʵ����
public class Customer {
      private Integer id;
      private String name;
      private String password;
      private String email;
      private String qq;
      
      public Customer(){
    	  
      }
      
      public Customer(Integer id, String name, String password, String email,
			String qq) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.qq = qq;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	//����
    @Override
    public String toString() {
    	return "customer-->"+"����:"+name+" ����:"+password+" Email:"+email+"  qq:"+qq;
    }  
}

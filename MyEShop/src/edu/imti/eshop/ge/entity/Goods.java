package edu.imti.eshop.ge.entity;
//商品（tbl_goods）的实体类
public class Goods {
	//商品的编号
     private Integer goodsId;
     //商品的名称
     private String name;
     //商品的单价
     private double price;
     //商品的类别：书籍、服装、数码、零食、鞋帽....
     private String type;
     //商品图片的路径
     private String imgPath;
     
     public Goods(){}

	public Goods(Integer goodsId, String name, double price, String type,
			String imgPath) {
		super();
		this.goodsId = goodsId;
		this.name = name;
		this.price = price;
		this.type = type;
		this.imgPath = imgPath;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	//测试
     @Override
    public String toString() {
    	String str = "goods-->"+"商品编号:"+goodsId+" 商品名称:"+name+" 商品类型:"+type+" 商品单价:"+price+" 商品图片:"+imgPath;
    	return str;
    }
     
}

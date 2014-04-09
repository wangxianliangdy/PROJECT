package edu.imti.eshop.ge.entity;
//��Ʒ��tbl_goods����ʵ����
public class Goods {
	//��Ʒ�ı��
     private Integer goodsId;
     //��Ʒ������
     private String name;
     //��Ʒ�ĵ���
     private double price;
     //��Ʒ������鼮����װ�����롢��ʳ��Ьñ....
     private String type;
     //��ƷͼƬ��·��
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
	
	//����
     @Override
    public String toString() {
    	String str = "goods-->"+"��Ʒ���:"+goodsId+" ��Ʒ����:"+name+" ��Ʒ����:"+type+" ��Ʒ����:"+price+" ��ƷͼƬ:"+imgPath;
    	return str;
    }
     
}

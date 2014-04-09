package edu.imti.eshop.ge.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Goods;

public class AddNewGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest httprequest, HttpServletResponse response)
			throws ServletException, IOException {
		//创建SmartUpload对象
		SmartUpload su = new SmartUpload();
		//上传初始化
		su.initialize(getServletConfig(), httprequest, response);
		/**设定上传限制**/
		//设定允许上传的文件类型
		su.setAllowedFilesList("jpg,JPG,gif,GIF,bmp,BMP");
		try {
			//设置不允许上传的文件类型
			su.setDeniedFilesList("exe,bat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//设置单个上传文件的最大上限（5M）
		su.setMaxFileSize(1024*1024*5);
		//设置所有上传文件的最大上限（20M）
		su.setTotalMaxFileSize(1024*1024*20);
		try {
			//将文件从客户端上传到服务器端
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		//获得上传的文件
//		Files files = su.getFiles();
//		File file = files.getFile(0);
		File file = su.getFiles().getFile(0);
		String imgName = null;
		//只有上传图片才执行以下代码
		if(file != null && file.getSize() > 0){
			/**获得文件的全称--前缀+扩展名**/
			String imgNamePrefix = UUID.randomUUID().toString();
			String imgNameExt = file.getFileExt();
			imgName = imgNamePrefix + "." +imgNameExt;
			try {
				//将上传文件保存到服务器端的指定位置
				file.saveAs("/daemon/images/"+imgName, su.SAVE_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		
		
		//获得上传对象信息
		Request request = su.getRequest();
		
		//获得提交的商品信息
		String name = request.getParameter("name");
		String type = request.getParameter("type");
        String str_price = request.getParameter("price");
        
        //转换数据类型
        Double price = Double.parseDouble(str_price);
        
        //将提交的商品信息封装成一个Goods对象
        Goods goods = new Goods();
        goods.setName(name);
        goods.setType(type);
        goods.setPrice(price);
        goods.setImgPath(imgName);
        
        //调用dao对象中添加商品的方法
        IGoodsDao goodsDao = new GoodsDaoImpl();
        goodsDao.addNewGoods(goods);
        
        //将所有商品信息保存到容器中
        List<Goods> list = goodsDao.selectAllGoods();
        httprequest.setAttribute("LIST", list);
        
        //添加操作完成之后，跳转
        response.sendRedirect("/MyEShop/daemon/servlet/showGoodsListUIServlet");
      
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//重写上面的doGet()方法
		doGet(request, response);
	}

}

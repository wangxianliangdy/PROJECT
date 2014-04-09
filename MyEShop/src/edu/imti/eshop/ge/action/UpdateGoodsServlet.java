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
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import edu.imti.eshop.ge.dao.GoodsDaoImpl;
import edu.imti.eshop.ge.dao.IGoodsDao;
import edu.imti.eshop.ge.entity.Goods;

public class UpdateGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest httprequest, HttpServletResponse response)
			throws ServletException, IOException {
		//����SmartUpload����
		SmartUpload su = new SmartUpload();
		//�ϴ���ʼ��
		su.initialize(getServletConfig(), httprequest, response);
		/**�趨�ϴ�����**/
		//�趨�����ϴ����ļ�����
		su.setAllowedFilesList("jpg,JPG,GIF,gif,BMP,bmp");
		try {
			//���ò������ϴ����ļ�����
			su.setDeniedFilesList("exe,bat");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//���õ����ϴ��ļ���������ޣ�5M��
		su.setMaxFileSize(1024*1024*5);
		//���������ϴ��ļ���������ޣ�20M��
		su.setTotalMaxFileSize(1024*1024*20);
		try {
			//���ļ��ӿͻ����ϴ�����������
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		//����ϴ����ļ�
		Files files = su.getFiles();
		File file = files.getFile(0);
//		File file = su.getFiles().getFile(0);
		String imgName = null;
		//ֻ���޸�ͼƬ��Ϣ�Ż�ִ�����´���
		if(file != null && file.getSize() > 0){
			/**����ļ���ȫ��--ǰ׺+��չ��**/
			String imgNamePrefix = UUID.randomUUID().toString();
			String imgNameExt = file.getFileExt();
			imgName = imgNamePrefix + "." +imgNameExt;
			
			try {
				//���ϴ��ļ����浽�������˵�ָ��λ��
				file.saveAs("/daemon/images/"+imgName, su.SAVE_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}else{
			imgName = httprequest.getParameter("imgPath");
			//����
			System.out.println("�޸���Ʒ��Ϣ������ͼƬ·����"+imgName);
			
			try {
				//���ϴ��ļ����浽�������˵�ָ��λ��
				file.saveAs("/daemon/images/"+imgName, su.SAVE_VIRTUAL);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		
		
		//����ϴ�������Ϣ
		Request request = su.getRequest();
		
		
		
		//��ø��ĺ����ύ����Ʒ��Ϣ
		String str_id = request.getParameter("goodsId");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String str_price = request.getParameter("price");
		//ת����������
		Integer goodsId = Integer.parseInt(str_id);
		Double price = Double.parseDouble(str_price);
		
		//�����ĺ����Ʒ��Ϣ��װ��һ����Ʒ����
		Goods goods = new Goods();
		goods.setGoodsId(goodsId);
		goods.setName(name);
		goods.setType(type);
		goods.setPrice(price);
		goods.setImgPath(imgName);
		
		//����dao����
		IGoodsDao goodsDao = new GoodsDaoImpl();
		goodsDao.updateGoods(goods);
		
		//��goods���϶��󱣴浽������
		List<Goods> list = goodsDao.selectAllGoods();
		httprequest.setAttribute("LIST", list);
		
		//�޸Ĳ������֮����ת
		response.sendRedirect("/MyEShop/daemon/servlet/showGoodsListUIServlet");
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��д�����doGet()����
		doGet(request, response);
	}

}

package com.oeong.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.oeong.entity.OEONG_PRODUCT;
import com.oeong.service.OEONG_PRODUCTDao;

/**
 * Servlet implementation class DoProductAdd
 */
@WebServlet("/manage/admin_doproductadd")
public class DoProductAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建SmartUpload对象
		SmartUpload su = new SmartUpload();
		
		// 初始化
		su.initialize(this.getServletConfig(), request, response);
		
		// 上传过程
		try {
			su.upload();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 获取上传的文件对象
		Files fs = su.getFiles();
		File f = fs.getFile(0);
		
		// 获取上传的文件名称
		String fname = f.getFileName();
//		System.out.println(fname);
		try {
			su.save("images/product");
			// 上传的图片保存在E:\Java\Eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Shop\images\product
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Request req1 = su.getRequest();
		String pname = req1.getParameter("productName");
		String id = req1.getParameter("parentId");
		String price = req1.getParameter("productPrice");
		String desc = req1.getParameter("productDesc");
		String stock = req1.getParameter("productStock");
	
		OEONG_PRODUCT p = new OEONG_PRODUCT(
				0,	// 数据库设置null，自动增长
				pname,
				desc,
				Integer.parseInt(price),
				Integer.parseInt(stock),
				Integer.parseInt(id.split("-")[0]),
				Integer.parseInt(id.split("-")[1]),
				fname
		);
		int count = OEONG_PRODUCTDao.insert(p);
		
		if(count > 0) {
			response.sendRedirect("admin_productselect");
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('商品添加失败');");
			out.write("location.href='admin_toproductadd';");
			out.write("</script>");
		}
	}
}

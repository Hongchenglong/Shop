package com.oeong.servlet.cate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oeong.entity.OEONG_CATEGORY;
import com.oeong.service.OEONG_CATEGORYDao;

/**
 * Servlet implementation class DoUserCate
 */
@WebServlet("/manage/admin_docateadd")
public class DoCateadd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 接收参数
		int fid = Integer.parseInt(request.getParameter("parentId"));
		String name = request.getParameter("className");
		
		OEONG_CATEGORY cate = new OEONG_CATEGORY(0, name, fid);
		OEONG_CATEGORYDao.insert(cate);
		
		response.sendRedirect("admin_cateselect");
	}
}

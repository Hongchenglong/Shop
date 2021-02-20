package com.oeong.servlet.cate;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oeong.entity.OEONG_CATEGORY;
import com.oeong.service.OEONG_CATEGORYDao;

/**
 * Servlet implementation class ToCateUpdate
 */
@WebServlet("/manage/admin_tocateupdate")
public class ToCateUpdate extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收参数
		int id = Integer.parseInt(request.getParameter("id"));
		OEONG_CATEGORY cate = OEONG_CATEGORYDao.selectById(id);
		ArrayList<OEONG_CATEGORY> catelist = OEONG_CATEGORYDao.selectALL();
		request.setAttribute("c", cate);
		request.setAttribute("catelist", catelist);
		
		request.getRequestDispatcher("admin_catemodify.jsp").forward(request, response);
	}
}

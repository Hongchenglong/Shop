package com.oeong.servlet.product;

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
 * Servlet implementation class ToProductAdd
 */
@WebServlet("/manage/admin_toproductadd")
public class ToProductAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<OEONG_CATEGORY> flist = OEONG_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<OEONG_CATEGORY> clist = OEONG_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		request.getRequestDispatcher("admin_productadd.jsp").forward(request, response);;
	}
}

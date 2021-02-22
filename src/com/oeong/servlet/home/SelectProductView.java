package com.oeong.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oeong.entity.OEONG_CATEGORY;
import com.oeong.entity.OEONG_PRODUCT;
import com.oeong.service.OEONG_CATEGORYDao;
import com.oeong.service.OEONG_PRODUCTDao;

/**
 * Servlet implementation class SelectProductView
 */
@WebServlet("/selectproductview")
public class SelectProductView extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<OEONG_CATEGORY> flist = OEONG_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<OEONG_CATEGORY> clist = OEONG_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);

		String id = request.getParameter("id");
		OEONG_PRODUCT p = null;
		if (id != null) {
			p = OEONG_PRODUCTDao.selectById(Integer.parseInt(id));
			request.setAttribute("p", p);
		}
		
		request.getRequestDispatcher("productview.jsp").forward(request, response);
	}
}

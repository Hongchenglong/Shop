package com.oeong.servlet.home;

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
 * Servlet implementation class SelectProductList
 */
@WebServlet("/selectproductlist")
public class SelectProductList extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<OEONG_CATEGORY> flist = OEONG_CATEGORYDao.selectCate("father");
		request.setAttribute("flist", flist);
		ArrayList<OEONG_CATEGORY> clist = OEONG_CATEGORYDao.selectCate("child");
		request.setAttribute("clist", clist);
		
		String fid = request.getParameter("fid");
		String cid = request.getParameter("cid");
		
		int id = 0;
		if (fid != null) {
			id = Integer.parseInt(fid);
		}
		
		if (cid != null) {
			id = Integer.parseInt(cid);
		}
		
		request.setAttribute("title", OEONG_CATEGORYDao.selectById(id).getCATE_NAME());
		
		
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}
}

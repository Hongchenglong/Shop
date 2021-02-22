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
		ArrayList<OEONG_PRODUCT> list = null;
		if (fid != null) {
			id = Integer.parseInt(fid);
			list = OEONG_PRODUCTDao.selectByFid(id);
		}
		
		if (cid != null) {
			id = Integer.parseInt(cid);
			list = OEONG_PRODUCTDao.selectByCid(id);
		}
		
		request.setAttribute("title", OEONG_CATEGORYDao.selectById(id).getCATE_NAME());
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("productlist.jsp").forward(request, response);
	}
}

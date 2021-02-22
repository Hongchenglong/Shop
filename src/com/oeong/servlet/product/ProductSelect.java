package com.oeong.servlet.product;

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
 * Servlet implementation class ProductSelect
 */
@WebServlet("/manage/admin_productselect")
public class ProductSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<OEONG_PRODUCT> plist = OEONG_PRODUCTDao.selectALL();
		request.setAttribute("plist", plist);
		request.getRequestDispatcher("admin_product.jsp").forward(request, response);
	}
}

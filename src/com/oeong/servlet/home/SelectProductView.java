package com.oeong.servlet.home;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		if (p != null) {
			int cid = p.getPRODUCT_CID();
			// 为你推荐：推荐二级同类产品
			ArrayList<OEONG_PRODUCT> classlist = OEONG_PRODUCTDao.selectByCid(cid);
			request.setAttribute("classlist", classlist);

			OEONG_CATEGORY cate = OEONG_CATEGORYDao.selectById(cid);
			request.setAttribute("cate", cate);
		}

		// 最近访问
		// 从session获取ids
		HttpSession session = request.getSession();
		ArrayList<Integer> ids = (ArrayList<Integer>) session.getAttribute("ids");
		if (ids == null) {
			ids = new ArrayList<Integer>();
		}
		// 最多放5，如果多出5个将第一个删除
		if (ids.size() >= 5) {
			ids.remove(0);
		}
		// 存在则不添加
		if (id != null && (!ids.contains(Integer.parseInt(id)))) {
			ids.add(Integer.parseInt(id));
		}
		session.setAttribute("ids", ids);
		ids = (ArrayList<Integer>) session.getAttribute("ids");
		if (ids != null) {
			ArrayList<OEONG_PRODUCT> lastlylist = OEONG_PRODUCTDao.selectAllById(ids);
			request.setAttribute("lastlylist", lastlylist);
		}

		request.getRequestDispatcher("productview.jsp").forward(request, response);
	}
}

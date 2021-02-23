package com.oeong.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oeong.entity.OEONG_CART;
import com.oeong.entity.OEONG_USER;
import com.oeong.service.OEONG_CARTDao;

/**
 * Servlet implementation class OrderSelect
 */
@WebServlet("/orderselect")
public class OrderSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		HttpSession session = request.getSession();
		String isLogin = (String) session.getAttribute("isLogin");
		OEONG_USER user = (OEONG_USER) session.getAttribute("name");
		String eids = request.getParameter("eids");
		
		if (user != null && isLogin.equals("1")) {
			int totalprice = 0;
			String[] ids = eids.split(",");
			ArrayList<OEONG_CART> list = new ArrayList<OEONG_CART>();
			
			for (String id : ids) {
				OEONG_CART es = OEONG_CARTDao.getCartShop(id);
				int dprice = es.getCart_p_price() * es.getCart_quantity();
				totalprice += dprice;
				list.add(es);
			}
			request.setAttribute("shoplist", list);
			request.setAttribute("totalprice", totalprice);
			request.getRequestDispatcher("order.jsp").forward(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('请先登录');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			out.close();
		}
	}
}

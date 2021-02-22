package com.oeong.servlet.cart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oeong.entity.OEONG_CART;
import com.oeong.entity.OEONG_PRODUCT;
import com.oeong.entity.OEONG_USER;
import com.oeong.service.OEONG_CARTDao;
import com.oeong.service.OEONG_PRODUCTDao;

/**
 * Servlet implementation class CartAdd
 */
@WebServlet("/cartadd")
public class CartAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		OEONG_PRODUCT p = null;
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		String url = request.getParameter("url");

		HttpSession session = request.getSession();
		String isLogin = (String) session.getAttribute("isLogin");
		OEONG_USER user = (OEONG_USER) session.getAttribute("name");

		if (user != null && isLogin.equals("1")) {
			String uid = user.getUSER_ID();
			if (id != null) {
				p = OEONG_PRODUCTDao.selectById(Integer.parseInt(id));
				OEONG_CART cart = new OEONG_CART(
						0, p.getPRODUCT_FILENAME(), p.getPRODUCT_NAME(), p.getPRODUCT_PRICE(),
						Integer.parseInt(count), p.getPRODUCT_STOCK(), p.getPRODUCT_ID(), uid, 1);
				OEONG_CARTDao.insert(cart);
			}
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('用户登录失败');");
			out.write("location.href='login.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		
		if (url.equals("z")) {
			response.sendRedirect("showcart");
		} else {
			response.sendRedirect("selectproductview?id=" + id);
		}

	}
}
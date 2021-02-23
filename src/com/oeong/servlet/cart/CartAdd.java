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
		String pid = request.getParameter("id"); // 商品id
		String count = request.getParameter("count");
		String url = request.getParameter("url");

		HttpSession session = request.getSession();
		String isLogin = (String) session.getAttribute("isLogin");
		OEONG_USER user = (OEONG_USER) session.getAttribute("name");

		if (user != null && isLogin.equals("1")) {
			String uid = user.getUSER_ID();
			
			// 通过用户ID和购物车中的商品ID 查看有无此记录
			OEONG_CART srcsp = OEONG_CARTDao.getCartShop(uid, pid);
			if (srcsp != null) { // 源商品
				int srccount = srcsp.getCart_quantity();
				int newcount = srccount + Integer.parseInt(count);
				
				if (newcount > 5) newcount = 5;
				OEONG_CARTDao.updatenum(srcsp.getCart_id(), newcount);
			} else {
				if (pid != null) {
					p = OEONG_PRODUCTDao.selectById(Integer.parseInt(pid));
					OEONG_CART cart = new OEONG_CART(
							0, p.getPRODUCT_FILENAME(), p.getPRODUCT_NAME(), p.getPRODUCT_PRICE(),
							Integer.parseInt(count), p.getPRODUCT_STOCK(), p.getPRODUCT_ID(), uid, 1);
					OEONG_CARTDao.insert(cart);
				}
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
			response.sendRedirect("selectproductview?id=" + pid);
		}

	}
}

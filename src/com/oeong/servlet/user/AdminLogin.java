package com.oeong.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oeong.entity.OEONG_USER;
import com.oeong.service.OEONG_USERDao;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");

		int count = OEONG_USERDao.selectByNM(userName, passWord);

		if (count > 0) {
			HttpSession session = request.getSession();
			OEONG_USER user = OEONG_USERDao.selectAdmin(userName, passWord);
			session.setAttribute("name", user);
			session.setAttribute("isLogin", "1");
			
			if (user.getUSER_STATUS() == 2) { // 管理员
				session.setAttribute("isAdminLogin", "1");
				response.sendRedirect("/Shop/manage/admin_index.jsp");
			} else {
				response.sendRedirect("/Shop/index.jsp");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('用户登录失败');");
			out.write("location.href='/Shop/manage/admin_login.jsp';");
			out.write("</script>");
		}
	}

}

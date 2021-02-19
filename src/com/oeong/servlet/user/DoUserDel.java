package com.oeong.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oeong.entity.OEONG_USER;
import com.oeong.service.OEONG_USERDao;

/**
 * Servlet implementation class DoUserDel
 */
@WebServlet("/manage/admin_douserdel")
public class DoUserDel extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 接收参数
		String id = request.getParameter("id");

		int count = OEONG_USERDao.del(id);

		// 成功或失败 重定向到哪
		if (count > 0) {
			response.sendRedirect("admin_douserselect?cp=" + request.getParameter("cpage"));
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('用户删除失败')");
			out.write("location.href='manage/admin_douserselect?cp=" + request.getParameter("cpage") + "'"); // ?
			out.write("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 接收参数
		String ids[] = request.getParameterValues("id[]");

		for (String id : ids) {
			OEONG_USERDao.del(id);
		}

		// 成功或失败 重定向到哪
		response.sendRedirect("admin_douserselect");
	}
}

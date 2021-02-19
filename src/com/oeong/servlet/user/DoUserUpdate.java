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
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class DoUserUpdate extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		// 接收参数
		String username = request.getParameter("userName");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String userStatus = request.getParameter("userStatus");
		String cpage = request.getParameter("cpage");

		int status = 1;
		if(userStatus != null) {
			status = Integer.parseInt(userStatus);
		}
		
		// 创建用户实体
		OEONG_USER u = new OEONG_USER(username, name, pwd, sex, birthday, null, email, mobile, address, status);
		
		// 加入数据库中的用户表
		int count = OEONG_USERDao.update(u);

		// 成功或失败 重定向到哪
		if (count > 0) {
			response.sendRedirect("admin_douserselect?cp="+cpage);
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script type=\"text/javascript\">");
			out.write("alert('用户修改失败');");
			out.write("console.log('用户修改失败');");
			out.write("location.href='manage/admin_touserupdate?id="+username+"'");
			out.write("</script>");
		}
	}
}

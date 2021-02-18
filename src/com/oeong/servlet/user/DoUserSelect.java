package com.oeong.servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oeong.entity.OEONG_USER;
import com.oeong.service.OEONG_USERDao;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int cpage = 1; // 当前页
		int count = 10; // 每页显示条数
		
		// 获取用户指定页面
		String cp = request.getParameter("cp");
		if (cp != null) {
			cpage = Integer.parseInt(cp);
		}

		String keywords = request.getParameter("keywords");
		int arr[] = OEONG_USERDao.totalPage(count, keywords);

		// 获取所有用户记录
		ArrayList<OEONG_USER> list = OEONG_USERDao.selectALL(cpage, count, keywords);
		
		// 放到请求对象域里
		request.setAttribute("userlist", list); // 用户列表
		request.setAttribute("tsum", arr[0]); // 总记录数
		request.setAttribute("tpage", arr[1]); // 总页数
		request.setAttribute("cpage", cpage); // 当前页

		if (keywords != null) {
			request.setAttribute("searchParams", "&keywords="+keywords);
		}
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);
	}

}

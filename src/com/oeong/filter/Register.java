package com.oeong.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 用过滤器防止用户绕过表单
 * Servlet Filter implementation class Register
 */
@WebFilter("/register")
public class Register implements Filter {

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		// 设置字符集
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String userName = req.getParameter("userName");
		PrintWriter out = resp.getWriter();
		if (userName.equals("")) {
			out.write("<script>");
			out.write("alert('用户名不能为空');");
			out.write("location.href='reg.jsp'");
			out.write("</script>");
			out.close();
			return;
		}
		
		String verycode = req.getParameter("verycode");
		HttpSession session = req.getSession();
		String sysCode = (String)session.getAttribute("code");
		if (sysCode.equals(verycode)) {
			out.write("<script>");
			out.write("alert('验证码输入有误');");
			out.write("location.href='reg.jsp'");
			out.write("</script>");
			out.close();
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response); // 通过，不通过则直接return;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

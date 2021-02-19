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
 * 过滤器，防止未登录，而通过url可以进入后台
 * Servlet Filter implementation class AdminLogin
 */
@WebFilter("/manage/*")
public class AdminLogin implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLogin() {
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
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session = req.getSession();
		String flag = (String)session.getAttribute("isAdminLogin");
		
		String request_uri = req.getRequestURI();			  // /Shop/manage/login.jsp
		String ctxPath = req.getContextPath();				  // /Shop
		String uri = request_uri.substring(ctxPath.length()); // /manage/login.jsp
		
//		System.out.println(request_uri + "##" + ctxPath + "##" + uri); 
		if (uri.contains("admin_")) {
			if (flag != null && flag.equals("1")) {
				chain.doFilter(request, response); 
			} else {
				PrintWriter out = resp.getWriter();
				out.write("<script>");
				out.write("alert('请先登录');");
				out.write("location.href='login.jsp'");
				out.write("</script>");
				out.close();
				return;
			}
		} else {
			chain.doFilter(request, response); 
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

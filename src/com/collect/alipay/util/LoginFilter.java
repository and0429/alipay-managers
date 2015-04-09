package com.collect.alipay.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.collect.alipay.domain.Loginer;

/**
 * 登录过滤器<br/>
 * Servlet Filter implementation class LoginFilter
 * 
 * @author zhangkai
 */
public class LoginFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		String requestUrl = req.getRequestURI();

		if (requestUrl.endsWith("login.do") || requestUrl.contains("/assets") || requestUrl.endsWith("/alipayNotify.do") || requestUrl.endsWith("getloginer.do")
				|| requestUrl.endsWith(".js")) {
			chain.doFilter(request, response);
		} else {
			Loginer lognier = (Loginer) session.getAttribute("loginer");
			if (lognier == null) {
				resp.sendRedirect(req.getContextPath() + "/login.do");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

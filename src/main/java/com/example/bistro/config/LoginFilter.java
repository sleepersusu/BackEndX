package com.example.bistro.config;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/Bistro/*")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("進到LoginFilter");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);

		if (session != null && session.getAttribute("loginAccount") != null) {
			long maxInactiveInterval = session.getMaxInactiveInterval()* 1000; // 转换为毫秒
			session.setAttribute("maxInactiveInterval", maxInactiveInterval);
			long currentTime = System.currentTimeMillis();
			Long lastAccessTime = (Long) session.getAttribute("lastAccessTime");

			if (lastAccessTime != null) {

				long inactiveTime = currentTime - lastAccessTime;
				long remainingTime = maxInactiveInterval - inactiveTime;

				// 如果 session 超时，销毁 session
			    if (remainingTime <= 0) {
			        session.invalidate();
			        System.out.println("超時session銷毀");
			        httpResponse.sendRedirect("/"); // 重定向到登入頁面
			        return;
			    }
			}
			// 如果 session 存在，更新 lastAccessTime
			session.setAttribute("lastAccessTime", currentTime);
			chain.doFilter(request, response);
		}else {
            // Session 为 null，重定向到登录页面
            System.out.println("Session已被销毁！");
            httpResponse.sendRedirect("/");  // 重定向到登录页面
        }
	}

}

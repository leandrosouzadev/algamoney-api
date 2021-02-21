package com.algamoney.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	private String allowedOrigin = "http:localhost:8000"; // TODO: configure for different environments

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		httpServletResponse.setHeader("Access-Control-Allow-Origin", allowedOrigin);
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equals(httpServletRequest.getMethod())
				&& allowedOrigin.equals(httpServletRequest.getHeader("Origin"))) {
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-type, Accept");
			httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}

	}

}

package com.skillstorm.filters;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/api/cars")
public class CarFilters implements Filter {

	@Override
	public void destroy() {
		System.out.println("filter destroy()");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("filter FILTER()");
		
		HttpServletResponse resp = (HttpServletResponse) response;
		
		chain.doFilter(request, resp);	// forward the req/resp to the intended destination	
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init()");
		
	}

}

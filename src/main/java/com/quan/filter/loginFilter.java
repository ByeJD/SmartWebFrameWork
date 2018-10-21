package com.quan.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class loginFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(loginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter");
        logger.info("dddddddddddddddddddd");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}

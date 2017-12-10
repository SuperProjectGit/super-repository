package com.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author super
 * Create time 2017/12/10 15:41
 */
public class CustomerFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(CustomerFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        logger.info("customer do filter");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

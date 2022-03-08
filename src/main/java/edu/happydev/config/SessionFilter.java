package edu.happydev.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(SessionFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();
        LOGGER.info("inside filter - " + uri);

        if (!(uri.equals("/MockFb")
                || uri.equals("/MockFb/")
                || uri.equals("/MockFb/login")
                || uri.equals("/MockFb/login/")
                || uri.equals("/MockFb/signup")
                || uri.equals("/MockFb/signup/"))) {

            if (httpRequest.getSession().getAttribute("email") == null) {
                httpResponse.sendRedirect("/MockFb");
            }

        }

        chain.doFilter(request, response);

    }

}
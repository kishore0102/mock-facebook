package edu.happydev.config;

import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class FilterConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new SessionFilter() };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        // TODO Auto-generated method stub
        return null;
    }

}

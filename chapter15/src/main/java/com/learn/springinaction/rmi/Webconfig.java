package com.learn.springinaction.rmi;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Webconfig implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("appServlet", new DispatcherServlet(ser))
    }
}

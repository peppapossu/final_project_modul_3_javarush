package org.project.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.project.model.Constants;
import org.project.model.DataBase;
import org.project.service.ConverterJson;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        DataBase dbDataBase = ConverterJson.jsonConverter();

        ctx.setAttribute(Constants.ROOT, dbDataBase);
        ServletContextListener.super.contextInitialized(sce);
    }
}
package org.project.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.project.model.Constants;
import org.project.model.Root;
import org.project.service.ConverterJson;

import java.util.ResourceBundle;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();

        //ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

//        UserValidator userValidator = new UserValidator();
//        UserService userService = new UserService(userValidator, resourceBundle);
        Root dbRoot = ConverterJson.jsonConverter();

        ctx.setAttribute(Constants.ROOT, dbRoot);

        ServletContextListener.super.contextInitialized(sce);
    }
}
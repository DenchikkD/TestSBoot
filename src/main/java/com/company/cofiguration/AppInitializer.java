package com.company.cofiguration;

/**
 * Created by Onlin on 03.11.2016.
 */
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer
//        implements WebApplicationInitializer
{
//
//    public void onStartup(ServletContext container) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(AppConfig.class);
//        ctx.setServletContext(container);
//
//        ServletRegistration.Dynamic servlet = container.addServlet(
//                "dispatcher", new DispatcherServlet(ctx));
//
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//        servlet.addMapping("/authorization");
//        servlet.addMapping("/registration");
//        servlet.addMapping("/doAuthorization");
//        servlet.addMapping("/makeChanges");
//        servlet.addMapping("/greeting");
//        servlet.addMapping("/add");
//    }

}

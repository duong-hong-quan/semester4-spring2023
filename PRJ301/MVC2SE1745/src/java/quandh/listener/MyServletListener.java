/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quandh.listener;

import java.io.IOException;
import java.io.InputStream;
import java.security.AuthProvider;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author LAPTOP_HONGQUAN
 */
public class MyServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application is deploying................");
        ServletContext context = sce.getServletContext();
        String siteMapsPath = context.getInitParameter("SITE_MAPS_FILE_PATH");
        Properties properties = new Properties();

        InputStream is = null;
        context.setAttribute("SITEMAPS", properties);
        try {
            is = context.getResourceAsStream(siteMapsPath);
            properties.load(is);

        } catch (IOException ex) {
            context.log("MyServletListner IO" + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    context.log("MyServletListner IO" + e.getMessage());
                }
            }

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is deployed................");

    }
}

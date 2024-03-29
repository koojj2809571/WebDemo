package servlet.ch08;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String,String> countries = new HashMap<>();
        countries.put("ca","Canada");
        countries.put("us","United States");

        context.setAttribute("countries",countries);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

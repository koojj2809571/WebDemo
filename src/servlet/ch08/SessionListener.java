package servlet.ch08;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionListener implements HttpSessionListener, ServletContextListener {

    private static final String USER_COUNTER = "userCounter";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute(USER_COUNTER,new AtomicInteger());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionCounterChange(se,true);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionCounterChange(se,false);
    }

    private void sessionCounterChange(HttpSessionEvent event,boolean isAdd){
        HttpSession session = event.getSession();
        ServletContext context = session.getServletContext();
        AtomicInteger userCounter = (AtomicInteger) context.getAttribute(USER_COUNTER);
        int userCount = isAdd ? userCounter.incrementAndGet() : userCounter.decrementAndGet();
        String action = isAdd ? "incremented to: " : "decremented to: ";
        System.out.println("userCounter " + action + userCount);
    }
}

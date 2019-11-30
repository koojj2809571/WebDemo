package servlet.ch01;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "servlet.ch01.ConfigDemoServlet",
        urlPatterns = {"/config"},
        initParams = {
                @WebInitParam(name = "admin",value = "harry potter"),
                @WebInitParam(name = "email", value = "admin@example.com")
        }
)
public class ConfigDemoServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws IOException {
        ServletConfig config = getServletConfig();
        String admin = config.getInitParameter("admin");
        String email = config.getInitParameter("email");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println(
                "<html>" +
                    "<header></header>" +
                    "<body>" +
                        "Admin: " + admin + "<br/> Email: " + email +
                    "</body>" +
                "</html>"
        );
    }

}

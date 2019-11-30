package servlet.ch02;

import servlet.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CookieClassServlet", urlPatterns = "/cookieClass")
public class CookieClassServlet extends HttpServlet {
    private static final long serialVersionUID = 5L;

    private String[] methods = {"clone", "getComment", "getDomain",
            "getMaxAge", "getName", "getPath",
            "getSecure", "getValue", "getVersion",
            "isHttpOnly", "setComment", "setDomain",
            "setHttpOnly", "setMaxAge", "setPath",
            "setSecure", "setValue", "setVersion"
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] cookies = req.getCookies();
        Cookie maxRecordsCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("maxRecords")) {
                    maxRecordsCookie = cookie;
                    break;
                }
            }
        }

        int maxRecords = 5;
        if (maxRecordsCookie != null) {
            maxRecords = Integer.parseInt(maxRecordsCookie.getValue());
        }

        resp.setContentType(Constant.TEXT_HTML);
        PrintWriter writer = resp.getWriter();
        writer.println(
                "<html><head>" + "<title>Cookie Class</title>"
                        + "</head><body>"
                        + PreferenceServlet.MENU
                        + "<div>Here are some of the methods in " +
                        "javax.servlet.http.Cookie");
        writer.print("<ul>");
        for (int i = 0; i < maxRecords; i++){
            writer.print("<li>" + methods[i] + "</li>");
        }
        writer.print("</ul>");
        writer.print("</div></body></html>");

    }
}

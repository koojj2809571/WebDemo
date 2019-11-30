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

@WebServlet(name = "CookieInfoServlet", urlPatterns = "/cookieInfo")
public class CookieInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 6L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        StringBuilder styles = new StringBuilder();
        styles.append(".title{");
        if (cookies != null){
            for (Cookie cookie : cookies){
                String name = cookie.getName();
                String value = cookie.getValue();
                switch (name) {
                    case "titleFontSize":
                        styles.append("font-size:").append(value).append(";");
                        break;
                    case "titleFontWeight":
                        styles.append("font-weight:").append(value).append(";");
                        break;
                    case "titleFontStyle":
                        styles.append("font-style:").append(value).append(";");
                        break;
                }
            }
        }
        styles.append("}");

        resp.setContentType(Constant.TEXT_HTML);
        PrintWriter writer = resp.getWriter();
        writer.println(
                "<html><head>" + "<title>Cookie Info</title>"
                        + "<style>" + styles.toString() + "</style>"
                        + "</head><body>" + PreferenceServlet.MENU
                        + "<div class='title'>"
                        + "Session Management with Cookies:</div>");writer.print("<div>"
        );
        if (cookies == null) {
            writer.print("No cookie in this HTTP response.");
        } else {
            writer.println("<br/>Cookies in this HTTP response:");
            for (Cookie cookie : cookies) {
                writer.println("<br/>" + cookie.getName() + ":"
                        + cookie.getValue());
            }
        }
        writer.print("</div>");
        writer.print("</body></html>");
    }
}

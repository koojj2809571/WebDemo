package servlet.ch02;

import servlet.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PostFormDataDemo", urlPatterns = "/postFormData")
public class PostFormDataDemo extends HttpServlet {

    private static final long serialVersionUID = 4L;

    public PostFormDataDemo() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType(Constant.TEXT_UTF);

        PrintWriter writer = resp.getWriter();
        String title = "使用 GET 方法读取表单数据";
        String name = new String(req.getParameter("name").getBytes("ISO8859-1"), "UTF-8");
        String url = req.getParameter("url");
        System.out.println(name + "  " + url);
        writer.println(
                "<html>" +
                        "<head><title>" + title + "</title></head>" +
                        "<body bgcolor='#f0f0f0'>" +
                        "<h1 align='center'>" + title + "</h1>" +
                        "<ul>" +
                        "  <li><b>站点名</b>："
                        + name +
                        "  <li><b>网址</b>："
                        + req.getParameter("url") +
                        "</ul>" +
                        "</body>" +
                        "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}

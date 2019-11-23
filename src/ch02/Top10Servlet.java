package ch02;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Top10Servlet", urlPatterns = "/top10")
public class Top10Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final String LONDON = "london";
    private final String PARIS = "pairs";

    private List<String> londonAttractions;
    private List<String> parisAttractions;

    @Override
    public void init() {
        londonAttractions = new ArrayList<>();
        londonAttractions.add("London1");
        londonAttractions.add("London2");
        londonAttractions.add("London3");
        londonAttractions.add("London4");
        londonAttractions.add("London5");
        londonAttractions.add("London6");
        londonAttractions.add("London7");
        londonAttractions.add("London8");
        londonAttractions.add("London9");
        londonAttractions.add("London10");
        parisAttractions = new ArrayList<>();
        parisAttractions.add("Pairs1");
        parisAttractions.add("Pairs2");
        parisAttractions.add("Pairs3");
        parisAttractions.add("Pairs4");
        parisAttractions.add("Pairs5");
        parisAttractions.add("Pairs6");
        parisAttractions.add("Pairs7");
        parisAttractions.add("Pairs8");
        parisAttractions.add("Pairs9");
        parisAttractions.add("Pairs10");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showAttractions(req, resp, req.getParameter("city"));
    }

    private void showAttractions(HttpServletRequest request, HttpServletResponse response, String city) throws IOException {
        if (city == null || !city.equals(LONDON) && !city.equals(PARIS)) {
            showMainPage(request, response);
            return;
        }
        int page = 1;
        String pageParameter = request.getParameter("page");
        if (pageParameter != null) {
            page = Integer.parseInt(pageParameter);
            if (page > 2) {
                page = 1;
            }
        }
        List<String> attractions = null;
        switch (city) {
            case LONDON:
                attractions = londonAttractions;
                break;
            case PARIS:
                attractions = parisAttractions;
                break;
            default:
                break;
        }
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        StringBuilder builder = new StringBuilder();
        builder.append("<html><header>");
        builder.append("<title>Top 10 Tourist Attractions</title>");
        builder.append("</header><body>");
        builder.append("<a href='top10'>Select City</a>");
        builder.append("<hr/>Page").append(page).append("<hr/>");
        int start = (page -1) * 5;
        for (int i = start; i < (start + 5); i++){
            builder.append(attractions.get(i)).append("<br/>");
        }
        builder.append("<hr style='color:blue'/>");
        builder.append("<a href='?city=").append(city).append("&page=1'>Page 1</a>");
        builder.append("<a href='?city=").append(city).append("&page=2'>Page 2</a>");
        builder.append("</body></html>");
        writer.println(builder.toString());
    }

    private void showMainPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String builder =
                "<html>" +
                    "<header>" +
                        "<title>Top 10 Tourist Attractions</title>" +
                    "</header>" +
                    "<body>" +
                        "Select City：" +
                        "<br/><a href='?city=london'>London</a>" +
                        "<br/><a href='?city=pairs'>Pairs</a>" +
                    "</body>" +
                "</html>";
        writer.println(builder);
    }

}

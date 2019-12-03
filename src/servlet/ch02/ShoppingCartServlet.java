package servlet.ch02;

import servlet.ch02.bean.Product;
import servlet.ch02.bean.ShoppingItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static servlet.Constant.TEXT_HTML;

@WebServlet(name = "ShoppingCartServlet",urlPatterns = {
        "/products","/viewProductDetails","/addToCart","/viewCart"
})
public class ShoppingCartServlet extends HttpServlet {
    private static final long serialVersionUID = 7L;

    private static final String CART_ATTRIBUTE = "cart";

    private List<Product> products = new ArrayList<>();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    @Override
    public void init() {
        products.add(new Product(1, "Bravo 32' HDTV","Low-cost HDTV from renowned TV manufacturer", 159.95F));
        products.add(new Product(2, "Bravo BluRay Player", "High quality stylish BluRay player", 99.95F));
        products.add(new Product(3, "Bravo Stereo System", "5 speaker hifi system with iPod player", 129.95F));
        products.add(new Product(4, "Bravo iPod player", "An iPod plug-in that can play multiple formats ", 39.95F));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith("/products")){
            sendProductsList(resp);
        }else if (uri.endsWith("/viewProductDetails")){
            sendProductDetail(req,resp);
        }else if ((uri.endsWith("/viewCart"))){
            sendShowCart(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int productId = Integer.parseInt(req.getParameter("id"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Product product = getProductById(productId);
        if (product != null && quantity >= 0){
            ShoppingItem item = new ShoppingItem(product,quantity);
            HttpSession session = req.getSession();
            List<ShoppingItem> cart = (List<ShoppingItem>)session.getAttribute(CART_ATTRIBUTE);
            if (cart == null){
                cart = new ArrayList<>();
                session.setAttribute(CART_ATTRIBUTE,cart);
            }
            cart.add(item);
        }
        sendProductsList(resp);
    }

    private Product getProductById(int productId) {
        for (Product product : products){
            if (product.getId() == productId){
                return product;
            }
        }
        return null;
    }

    private void sendProductsList(HttpServletResponse response) throws IOException {
        response.setContentType(TEXT_HTML);
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Products</title></head><body><h2>Products</h2>");
        writer.println("<ul>");
        for (Product product : products){
            writer.println("<li>" + product.getName() + "("
                    + currencyFormat.format(product.getPrice())
                    + ") (" + "<a href='viewProductDetails?id="
                    + product.getId() + "'>Details</a>)");
        }
        writer.println("</ul>");
        writer.println("<a href='viewCart'>View Cart</a>");
        writer.println("</body></html>");
    }

    private void sendProductDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType(TEXT_HTML);
        PrintWriter writer = response.getWriter();
        int productId = Integer.parseInt(request.getParameter("id"));
        Product product = getProductById(productId);
        if (product != null){
            writer.println("<html><head>"
                    + "<title>Product Details</title></head>"
                    + "<body><h2>Product Details</h2>"
                    + "<form method='post' action='addToCart'>");
            writer.println("<input type='hidden' name='id' value='" + productId + "'/>");
            writer.println("<table>");
            writer.println("<tr><td>Name:</td><td>"
                    + product.getName() + "</td></tr>");
            writer.println("<tr><td>Description:</td><td>"
                    + product.getDescription() + "</td></tr>");
            writer.println("<tr>" + "<tr>"
                    + "<td><input name='quantity'/></td>"
                    + "<td><input type='submit' value='Buy'/>"
                    + "</td>"
                    + "</tr>");
            writer.println("<tr><td colspan='2'>"
                    + "<a href='products'>Product List</a>"
                    + "</td></tr>");
            writer.println("</table>");
            writer.println("</form></body>");
        }else {
            writer.println("No product found");
        }
    }

    private void sendShowCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType(TEXT_HTML);
        PrintWriter writer = response.getWriter();
        writer.println("<html><head><title>Shopping Cart</title></head>");
        writer.println("<body><a href='products'>Product List</a>");
        HttpSession session = request.getSession();
        List<ShoppingItem> items = (List<ShoppingItem>) session.getAttribute(CART_ATTRIBUTE);
        if (items != null){
            writer.println("<table>");
            writer.println("<tr><td style='width:150px'>Quantity</td>"
                            + "<td style='width:150px'>Product</td>"
                            + "<td style='width:150px'>Price</td>"
                            + "<td>Amount</td></tr>");
            double total = 0.0;
            for (ShoppingItem shoppingItem : items) {
                Product product = shoppingItem.getProduct();
                int quantity = shoppingItem.getQuantity();
                if (quantity != 0) {
                    float price = product.getPrice();
                    writer.println("<tr>");
                    writer.println("<td>" + quantity + "</td>");
                    writer.println("<td>" + product.getName() + "</td>");
                    writer.println("<td>" + currencyFormat.format(price) + "</td>");
                    double subtotal = price * quantity;
                    writer.println("<td>" + currencyFormat.format(subtotal) + "</td>");
                    total += subtotal;
                    writer.println("</tr>");
                }
            }
            writer.println("<tr><td colspan='4' style='text-align:right'>Total:" + currencyFormat.format(total) + "</td></tr>");
            writer.println("</table>");
        }
        writer.println("</table></body></html>");
    }
}

package servlet.ch02;

import servlet.Constant;
import servlet.ch02.bean.Customer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer","/editCustomer","/updateCustomer"})
public class CustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 2L;

    private final String GET_LIST = "/customer";
    private final String EDIT = "/editCustomer";
    private final String UPDATE = "/updateCustomer";

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void init(){
        customers.add(new Customer(1,"Grooby","orc"));
        customers.add(new Customer(2,"Moon","ne"));
    }

    private void sendCustomerList(HttpServletResponse response) throws IOException {
        response.setContentType(Constant.TEXT_HTML);
        PrintWriter writer = response.getWriter();
        writer.println("[" + customers.get(0).toString() + "," + customers.get(1) + "]");
    }

    private Customer getCustomer(int id){
        for (Customer customer : customers){
            if (id == customer.getId()){
                return customer;
            }
        }
        return null;
    }

    private Customer getCustomerByReq(HttpServletRequest req){
        if (req == null){
            return null;
        }
        return getCustomer(Integer.parseInt(req.getParameter("id")));
    }

    private void sendEditCustomerForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(Constant.TEXT_HTML);
        PrintWriter writer = response.getWriter();
        Customer customer = getCustomerByReq(request);
        if (customer != null){
            writer.println(customer.toString());
        }else {
            writer.println("No Customer Found");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith(GET_LIST)){
            sendCustomerList(resp);
        }else if (uri.endsWith(EDIT)){
            sendEditCustomerForm(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        System.out.println(req.getParameter("id"));
        Customer customer = getCustomerByReq(req);
        if(uri.endsWith(UPDATE)) {
            if (customer != null) {
                customer.setName(req.getParameter("name"));
                customer.setCity(req.getParameter("city"));
            }
            sendCustomerList(resp);
        }
    }
}

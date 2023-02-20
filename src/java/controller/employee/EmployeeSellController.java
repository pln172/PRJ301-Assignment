/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import controller.BaseRequiredAuthController;
import dal.CustomerDBContext;
import dal.EmployeeDBContext;
import dal.OrderDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Customer;
import model.Employee;
import model.Group;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author phuon
 */
public class EmployeeSellController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        EmployeeDBContext edb = new EmployeeDBContext();
        Employee employee = edb.getEmployee(acc.getEmployee().getId());
        if (employee != null) {
            request.setAttribute("employee", employee);
        }

        CustomerDBContext cdb = new CustomerDBContext();
        ArrayList<Customer> customers = cdb.getCustomers();
        request.setAttribute("customers", customers);

        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Group> groups = pdb.getGroups();
        ArrayList<Product> products = pdb.getProducts();

        request.setAttribute("groups", groups);
        request.setAttribute("products", products);
        request.getRequestDispatcher("../view/employee/sell.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee e = new Employee();
        EmployeeDBContext edb = new EmployeeDBContext();
        e = edb.getEmployee(Integer.parseInt(request.getParameter("emp")));

        CustomerDBContext cdb = new CustomerDBContext();
        Customer c = cdb.getCus(Integer.parseInt(request.getParameter("cus")));

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSS");
        String formattedDate = myDateObj.format(dtf);
        Timestamp ts = Timestamp.valueOf(formattedDate);

        Order o = new Order();
        o.setEid(e);
        o.setCid(c);
        o.setDate(ts);

        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.getProducts();

        String str = "";
        String quantity_value = "";
        ArrayList<Integer> quantity = new ArrayList<>();
        for (Product product : products) {
            str = "quantity" + product.getId();
            quantity_value = request.getParameter(str);
            if (quantity_value != null && !quantity_value.equals("")) {
                if (Integer.parseInt(quantity_value) != 0) {
                    Product pro = pdb.getPro(Integer.parseInt(str.replace("quantity", "")));
                    OrderDetail od = new OrderDetail();
                    od.setOid(o);
                    od.setPid(pro);
                    od.setQuantity(Integer.parseInt(quantity_value));
                    od.setPrice(pro);
                    od.setTotal(od.getPrice().getPriceExport() * od.getQuantity());
                    o.getOrderDetails().add(od);
                    if (pro.getQuantity() > 0) {
                        pro.setQuantity(pro.getQuantity() - od.getQuantity());
                        pdb.update(pro);
                    }
                }
            }

        }

//        if (pros != null) {
//            for (int i = 0; i < pros.length; i++) {
//                if (Integer.parseInt(pros[i]) != 0 && Integer.parseInt(quantity[i]) != 0) {
//                    Product p = pdb.getPro(Integer.parseInt(pros[i]));
//                    OrderDetail od = new OrderDetail();
//                    od.setOid(o);
//                    od.setPid(p);
//                    od.setQuantity(Integer.parseInt(quantity[i]));
//                    od.setPrice(p);
//                    od.setTotal(od.getPrice().getPriceExport() * od.getQuantity());
//                    o.getOrderDetails().add(od);
//                    p.setQuantity(p.getQuantity() - od.getQuantity());
//                    pdb.update(p);
//                }
//            }
        int total = 0;
        for (OrderDetail od : o.getOrderDetails()) {
            total += od.getTotal();
        }

        o.setTotal(total);
        OrderDBContext odb = new OrderDBContext();
        odb.insert(o);

       Account acc = (Account) request.getSession().getAttribute("account");
        Employee employee = edb.getEmployee(acc.getEmployee().getId());
        if (employee != null) {
            request.setAttribute("employee", employee);
        }

        ArrayList<Customer> customers = cdb.getCustomers();
        request.setAttribute("customers", customers);

        ArrayList<Group> groups = pdb.getGroups();

        request.setAttribute("groups", groups);
        request.setAttribute("products", products);

//            request.setAttribute("err", request.getParameter("err"));
        request.setAttribute("mess", "Bán thành công!");
        request.getRequestDispatcher("../view/employee/sell.jsp").forward(request, response);
//        response.sendRedirect("sell");
    }
}

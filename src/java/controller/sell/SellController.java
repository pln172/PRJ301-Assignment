/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sell;

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
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author ASUS
 */
public class SellController extends BaseRequiredAuthController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        EmployeeDBContext edb = new EmployeeDBContext();
//        ArrayList<Employee> employees = edb.getEmployees();
//        request.setAttribute("employees", employees);

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
        ArrayList<Product> products = pdb.getProducts();
        request.setAttribute("products", products);

        request.setAttribute("err", request.getParameter("err"));
        request.getRequestDispatcher("view/sell/Sell.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee e = new Employee();
        if (!request.getParameter("emp").isEmpty()) {
            EmployeeDBContext edb = new EmployeeDBContext();
            e = edb.getEmployee(Integer.parseInt(request.getParameter("emp")));
        } else {
            e.setId(-1);
        }

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

        String[] pros = request.getParameterValues("pro");
        String[] quantity = request.getParameterValues("quantity");

        ProductDBContext pdb = new ProductDBContext();

        boolean check_quan = true;
        for (int i = 0; i < pros.length; i++) {
            if (Integer.parseInt(pros[i]) != 0) {
                Product p = pdb.getPro(Integer.parseInt(pros[i]));
                if (p.getQuantity() < Integer.parseInt(quantity[i])) {
                    check_quan = false;
                    break;
                }
            }
        }

        boolean check_dup = true;
        for (int i = 0; i < pros.length - 1; i++) {
            for (int j = i + 1; j < pros.length; j++) {
                if (!pros[i].equals("0")) {
                    if (pros[i].equals(pros[j])) {
                        check_dup = false;
                        break;
                    }
                }
            }
        }

        boolean check_noquan = true;
        for (int i = 0; i < pros.length; i++) {
            if ((pros[i].equals("0") && !quantity[i].equals("0"))) {
                check_noquan = false;
            }
        }

        if (check_dup && check_quan && check_noquan) {
            if (pros != null) {
                for (int i = 0; i < pros.length; i++) {
                    if (Integer.parseInt(pros[i]) != 0 && Integer.parseInt(quantity[i]) != 0) {
                        Product p = pdb.getPro(Integer.parseInt(pros[i]));
                        OrderDetail od = new OrderDetail();
                        od.setOid(o);
                        od.setPid(p);
                        od.setQuantity(Integer.parseInt(quantity[i]));
                        od.setPrice(p);
                        od.setTotal(od.getPrice().getPriceExport() * od.getQuantity());
                        o.getOrderDetails().add(od);
                        p.setQuantity(p.getQuantity() - od.getQuantity());
                        pdb.update(p);
                    }
                }
            }

            int total = 0;
            for (OrderDetail od : o.getOrderDetails()) {
                total += od.getTotal();
            }

            o.setTotal(total);
            OrderDBContext odb = new OrderDBContext();
            odb.insert(o);

//            Account acc = (Account) request.getSession().getAttribute("account");
//            if (acc.getUsername().equals("loandp")) {
//                response.sendRedirect("http://localhost:8080/ASSIGNMENT/history/sale");
//            } else {
//                response.sendRedirect("http://localhost:8080/ASSIGNMENT/sell");
//            }
            Account acc = (Account) request.getSession().getAttribute("account");
            EmployeeDBContext edb = new EmployeeDBContext();
            Employee employee = edb.getEmployee(acc.getEmployee().getId());
            if (employee != null) {
                request.setAttribute("employee", employee);
            }

            ArrayList<Customer> customers = cdb.getCustomers();
            request.setAttribute("customers", customers);

            ArrayList<Product> products = pdb.getProducts();
            request.setAttribute("products", products);

            request.setAttribute("err", request.getParameter("err"));
            request.setAttribute("mess", "Sell successfully!");
            request.getRequestDispatcher("view/sell/Sell.jsp").forward(request, response);
        } else if (!check_dup && check_quan && check_noquan) {
            response.sendRedirect("sell?err=1");
        } else if (!check_quan && check_dup && check_noquan) {
            response.sendRedirect("sell?err=2");
        } else if (!check_noquan && check_dup && check_quan) {
            response.sendRedirect("sell?err=3");
        } else if (!check_quan && !check_noquan && !check_dup) {
            response.sendRedirect("sell?err=4");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

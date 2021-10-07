/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sell;

import controller.BaseRequiredAuthController;
import dal.CustomerDBContext;
import dal.EmployeeDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;
import model.Employee;
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
        EmployeeDBContext edb = new EmployeeDBContext();
        ArrayList<Employee> employees = edb.getEmployees();
        request.setAttribute("employees", employees);

        CustomerDBContext cdb = new CustomerDBContext();
        ArrayList<Customer> customers = cdb.getCustomers();
        request.setAttribute("customers", customers);

        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.getProducts();
        request.setAttribute("products", products);
        
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
        e.setId(Integer.parseInt(request.getParameter("emp")));

        Customer c = new Customer();
        c.setId(Integer.parseInt(request.getParameter("cus")));
        
        String[] pros = request.getParameterValues("pro");
        if (pros != null) {
            for (String pro : pros) {
                Product p = new Product();
                
            }
        }
        
        
        response.sendRedirect("http://localhost:8080/ASSIGNMENT/sell/detail");
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

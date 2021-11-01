/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.customer;

import controller.BaseRequiredAuthController;
import dal.CustomerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Customer;

/**
 *
 * @author ASUS
 */
public class UpdateController extends BaseRequiredAuthController {

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
        LocalDate d = java.time.LocalDate.now();
        int day = d.getDayOfMonth();
        int month = d.getMonth().getValue();
        int year = d.getYear();
        String dateMax = (year-5) + "-" + month + "-" + day;
        String dateMin = (year-120) + "-" + month + "-" + day;

        int id = Integer.parseInt(request.getParameter("id"));
        
        CustomerDBContext cdb = new CustomerDBContext();
        Customer cus = cdb.getCus(id);
        
        request.setAttribute("customer", cus);
        request.setAttribute("dateMax", dateMax);
        request.setAttribute("dateMin", dateMin);
        request.getRequestDispatcher("../view/customer/update.jsp").forward(request, response);
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
        Customer c = new Customer();
        c.setId(Integer.parseInt(request.getParameter("id")));
        c.setName(request.getParameter("name").replaceAll("\\s\\s+", " ").trim());
        c.setGender(request.getParameter("gender").equals("male"));
        c.setDob(Date.valueOf(request.getParameter("dob")));
        c.setPhone(request.getParameter("phone"));
        c.setEmail(request.getParameter("email").trim());
        c.setAddress(request.getParameter("address").replaceAll("\\s\\s+", " ").trim());
        
        CustomerDBContext cdb = new CustomerDBContext();
        cdb.update(c);
        response.sendRedirect("http://localhost:8080/ASSIGNMENT/customer");
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

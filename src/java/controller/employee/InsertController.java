/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.employee;

import controller.BaseRequiredAuthController;
import dal.AccountDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;

/**
 *
 * @author ASUS
 */
public class InsertController extends BaseRequiredAuthController {

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
        
        String dateMax = (year-18) + "-" + month + "-" + day;
        String dateMin = (year-65) + "-" + month + "-" + day;
        
        request.setAttribute("dateMin", dateMin);
        request.setAttribute("dateMax", dateMax);
        request.getRequestDispatcher("../view/employee/insert.jsp").forward(request, response);
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
        e.setName(request.getParameter("name").replaceAll("\\s\\s+", " ").trim());
        e.setGender(request.getParameter("gender").equals("male"));
        e.setDob(Date.valueOf(request.getParameter("dob")));
        e.setPhone(request.getParameter("phone"));
        e.setEmail(request.getParameter("email").trim());
        e.setAddress(request.getParameter("address").replaceAll("\\s\\s+", " ").trim());
        e.setActive(request.getParameter("active").equals("yes"));

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = myDateObj.format(dtf);
        e.setStarting_date(Date.valueOf(formattedDate));

        AccountDBContext adb = new AccountDBContext();
        ArrayList<Account> accounts = adb.getAccounts();

        boolean isExist = false;
        for (Account a : accounts) {
            if (request.getParameter("username").equals(a.getUsername())) {
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            Account a = new Account();
            a.setUsername(request.getParameter("username"));
            a.setPassword(request.getParameter("password"));

            EmployeeDBContext edb = new EmployeeDBContext();
            edb.insert(e);

            adb.insert(a);
            response.sendRedirect("http://localhost:8080/ASSIGNMENT/employee");
        } else {
            response.getWriter().println("Account does exist. Please re-insert!");
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

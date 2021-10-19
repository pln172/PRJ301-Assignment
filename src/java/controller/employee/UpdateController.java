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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;

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
        int id = Integer.parseInt(request.getParameter("id"));

        EmployeeDBContext edb = new EmployeeDBContext();
        Employee e = edb.getEmployee(id);

        request.setAttribute("employee", e);
        request.getRequestDispatcher("../view/employee/update.jsp").forward(request, response);
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
        e.setId(Integer.parseInt(request.getParameter("id")));
        e.setName(request.getParameter("name"));
        e.setGender(request.getParameter("gender").equals("male"));
        e.setDob(Date.valueOf(request.getParameter("dob")));
        e.setPhone(request.getParameter("phone"));
        e.setEmail(request.getParameter("email"));
        e.setAddress(request.getParameter("address"));
        e.setActive(request.getParameter("active").equals("yes"));

        if (request.getParameter("active").equals("no")) {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = myDateObj.format(dtf);
            e.setLeaving_date(Date.valueOf(formattedDate));

            AccountDBContext adb = new AccountDBContext();
            Account acc = adb.getAccountEmp(Integer.parseInt(request.getParameter("id")));
            adb.delete(acc);
        }
        EmployeeDBContext edb = new EmployeeDBContext();
        edb.update(e);
        response.sendRedirect("http://localhost:8080/ASSIGNMENT/employee");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDBContext;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Employee;

/**
 *
 * @author ASUS
 */
public class ForgetPassController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgetPassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPassController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("view/ForgetPass.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        EmployeeDBContext edb = new EmployeeDBContext();
        ArrayList<Employee> employees = edb.getEmployees();
        AccountDBContext adb = new AccountDBContext();
        Account acc = new Account();

        boolean check = false;
        for (Employee e : employees) {
            if (e.getEmail().equals(email) && e.isActive()) {
                check = true;
                acc = adb.getAccountEmp(e.getId());
                break;
            }
        }
        
        if (email.equals("phuongloan517@gmail.com")) {
            check = true;
            acc = adb.getAccount("loandp", "150121");
        }

        if (!check) {
            request.setAttribute("message", "Email is not accepted!");
        } else {
            String subject = "SALE MANAGEMENT ACCOUNT";
            String message = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Please change password after receive this mail!</h3>\n"
                    + "    <div style=\"font-weight: bold;\">Username: " + acc.getUsername()
                    + "</div>\n"
                    + "    <div style=\"font-weight: bold;\">Password: " + acc.getPassword()
                    + "</div>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
            SendMail.send(email, subject, message, "plkns2108@gmail.com", "123456789Loan");
            request.setAttribute("message", "Please check email!");
        }
        
        request.setAttribute("email", email);
        request.getRequestDispatcher("view/ForgetPass.jsp").forward(request, response);
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

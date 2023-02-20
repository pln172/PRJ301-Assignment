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
        LocalDate d = java.time.LocalDate.now();
        int day = d.getDayOfMonth();
        int month = d.getMonth().getValue();
        int year = d.getYear();
        String dateMax = (year - 18) + "-" + month + "-" + day;
        String dateMin = (year - 65) + "-" + month + "-" + day;

        int id = Integer.parseInt(request.getParameter("id"));

        EmployeeDBContext edb = new EmployeeDBContext();
        Employee e = edb.getEmployee(id);

        request.setAttribute("employee", e);
        request.setAttribute("dateMax", Date.valueOf(dateMax));
        request.setAttribute("dateMin", Date.valueOf(dateMin));
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name").replaceAll("\\s\\s+", " ").trim();
        Boolean gender = request.getParameter("gender").equals("male");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").replaceAll("\\s\\s+", " ").trim();
        Boolean active = request.getParameter("active").equals("yes");

        Employee e = new Employee();
        e.setId(id);
        e.setName(name);
        e.setGender(gender);
        e.setDob(dob);
        e.setPhone(phone);
        e.setEmail(email);
        e.setAddress(address);
        e.setActive(active);

        boolean isExistE = false;
        EmployeeDBContext edb = new EmployeeDBContext();
        ArrayList<Employee> employees = edb.getEmployees();
        Employee employee = edb.getEmployee(id);

        for (Employee emp : employees) {
            if (!email.equals(employee.getEmail()) && !email.isEmpty()
                    && (emp.getEmail().equals(e.getEmail()) || e.getEmail().equals("phuongloan517@gmail.com"))) {
                isExistE = true;
                break;
            }
        }

        if (!isExistE) {
            if (request.getParameter("active").equals("no")) {
                try {
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDate = myDateObj.format(dtf);
                    e.setLeaving_date(Date.valueOf(formattedDate));

                    AccountDBContext adb = new AccountDBContext();
                    Account acc = adb.getAccountEmp(id);
                    adb.delete(acc);
                } catch (NullPointerException | IllegalStateException ise) {
                }
            }

            edb.update(e);
            String servletPath = request.getContextPath();
            servletPath += "/employee";
            response.sendRedirect(servletPath);
        } else {
            request.setAttribute("name", name);
            request.setAttribute("gender", request.getParameter("gender"));
            request.setAttribute("dob", dob);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("address", address);
            request.setAttribute("mess", "Email does exist. Please re-update!");
            request.getRequestDispatcher("../view/employee/insert.jsp").forward(request, response);
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

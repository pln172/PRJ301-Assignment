/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ImportDBContext;
import dal.OrderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class ReportController extends BaseRequiredAuthController {

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
            out.println("<title>Servlet ReportController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportController at " + request.getContextPath() + "</h1>");
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate now = java.time.LocalDate.now();
        Date date = Date.valueOf(now.toString());

        OrderDBContext odb = new OrderDBContext();
        int revenue = odb.getTotal(date, date);
        int invoice = odb.getNumOrder(date, date);

        ImportDBContext idb = new ImportDBContext();
        int capital = idb.getCapital(date, date);

        int interest = revenue - capital;
        request.setAttribute("revenue", revenue);
        request.setAttribute("invoice", invoice);
        request.setAttribute("capital", capital);
        request.setAttribute("interest", interest);
        request.setAttribute("today", now);
        request.getRequestDispatcher("view/Report.jsp").forward(request, response);
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
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");

        if (dateFrom.length() != 0 && dateTo.length() != 0) {
            Date from = Date.valueOf(dateFrom);
            Date to = Date.valueOf(dateTo);
            if (from.compareTo(to) <= 0) {
                OrderDBContext odb = new OrderDBContext();
                int revenue = odb.getTotal(from, to);
                int invoice = odb.getNumOrder(from, to);

                ImportDBContext idb = new ImportDBContext();
                int capital = idb.getCapital(from, to);

                int interest = revenue - capital;
                request.setAttribute("dateFrom", from);
                request.setAttribute("dateTo", to);
                request.setAttribute("revenue", revenue);
                request.setAttribute("invoice", invoice);
                request.setAttribute("capital", capital);
                request.setAttribute("interest", interest);
            } else {
                request.setAttribute("dateFrom", from);
                request.setAttribute("dateTo", to);
                request.setAttribute("err", "DateTo must after DateFrom!");
            }
        } else if (dateFrom.length()== 0 && dateTo.length() != 0) {
            Date to = Date.valueOf(dateTo);
            OrderDBContext odb = new OrderDBContext();
            int revenue = odb.getTotal(to, to);
            int invoice = odb.getNumOrder(to, to);

            ImportDBContext idb = new ImportDBContext();
            int capital = idb.getCapital(to, to);

            int interest = revenue - capital;
            request.setAttribute("dateTo", to);
            request.setAttribute("revenue", revenue);
            request.setAttribute("invoice", invoice);
            request.setAttribute("capital", capital);
            request.setAttribute("interest", interest);
        } else if (dateTo.length() == 0 && dateFrom.length() != 0) {
            Date from = Date.valueOf(dateFrom);
            OrderDBContext odb = new OrderDBContext();
            int revenue = odb.getTotal(from, from);
            int invoice = odb.getNumOrder(from, from);

            ImportDBContext idb = new ImportDBContext();
            int capital = idb.getCapital(from, from);

            int interest = revenue - capital;
            request.setAttribute("dateFrom", from);
            request.setAttribute("revenue", revenue);
            request.setAttribute("invoice", invoice);
            request.setAttribute("capital", capital);
            request.setAttribute("interest", interest);
        }
        LocalDate now = java.time.LocalDate.now();
        request.setAttribute("today", now);
        request.getRequestDispatcher("view/Report.jsp").forward(request, response);
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

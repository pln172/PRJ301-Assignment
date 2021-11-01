/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.history;

import controller.BaseRequiredAuthController;
import dal.OrderDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

/**
 *
 * @author ASUS
 */
public class SaleController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LocalDate now = java.time.LocalDate.now();
        
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }

        int page = Integer.parseInt(raw_page);
        int pageSize = 8;

        OrderDBContext odb = new OrderDBContext();
        ArrayList<Order> orders = odb.pagging(pageSize, page);

        int count = odb.numberRecord();
        int totalPage = (count % pageSize == 0) ? count / pageSize : (count / pageSize) + 1;

        request.setAttribute("orders", orders);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", page);
        request.setAttribute("today", now);
        request.getRequestDispatcher("../view/history/sale.jsp").forward(request, response);
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
        try {
            Date date = Date.valueOf(request.getParameter("date"));

            OrderDBContext odb = new OrderDBContext();
            ArrayList<Order> orders = odb.searchByDate(date);

            request.setAttribute("date", date);
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("../view/history/sale.jsp").forward(request, response);

        } catch (IllegalArgumentException iae) {
            response.sendRedirect("sale");
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

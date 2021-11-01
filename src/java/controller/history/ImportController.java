/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.history;

import controller.BaseRequiredAuthController;
import dal.ImportDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Import;

/**
 *
 * @author ASUS
 */
public class ImportController extends BaseRequiredAuthController {

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
        
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }

        int page = Integer.parseInt(raw_page);
        int pageSize = 8;

        ImportDBContext idb = new ImportDBContext();
        ArrayList<Import> imports = idb.pagging(pageSize, page);
        
        int count = idb.numberRecord();
        int totalPage = (count % pageSize==0)?count / pageSize:(count / pageSize)+1;
        
        request.setAttribute("imports", imports);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", page);
        request.setAttribute("today", now);
        request.getRequestDispatcher("../view/history/import.jsp").forward(request, response);
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

            ImportDBContext idb = new ImportDBContext();
            ArrayList<Import> imports = idb.searchByDate(date);

            request.setAttribute("date", date);
            request.setAttribute("imports", imports);
            request.getRequestDispatcher("../view/history/import.jsp").forward(request, response);
        } catch (IllegalArgumentException iae) {
            response.sendRedirect("import");
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

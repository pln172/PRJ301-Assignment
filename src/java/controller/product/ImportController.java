/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import controller.BaseRequiredAuthController;
import dal.ImportDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Import;
import model.Product;

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
        int id = Integer.parseInt(request.getParameter("id"));

        ProductDBContext pdb = new ProductDBContext();
        Product product = pdb.getPro(id);

        request.setAttribute("product", product);
        request.getRequestDispatcher("../view/product/import.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));

        ProductDBContext pdb = new ProductDBContext();
        Product pro = pdb.getPro(id);

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSSSSSSS");
        String formattedDate = myDateObj.format(dtf);
        Timestamp ts = Timestamp.valueOf(formattedDate);

        Import i = new Import();
        i.setPid(pro);
        i.setDate(ts);
        i.setQuantity(Integer.parseInt(request.getParameter("quantityImport")));
        ImportDBContext idb = new ImportDBContext();
        idb.insert(i);

        pro.setQuantity(pro.getQuantity() + Integer.parseInt(request.getParameter("quantityImport")));
        pdb.update(pro);

        response.sendRedirect("http://localhost:8080/ASSIGNMENT/product");

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

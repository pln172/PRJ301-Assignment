/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import controller.BaseRequiredAuthController;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ProductController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }

        int page = Integer.parseInt(raw_page);
        int pageSize = 7;

        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.pagging(pageSize, page);

        int count = pdb.numberRecord();
        int totalPage = (count % pageSize == 0) ? count / pageSize : (count / pageSize) + 1;

        request.setAttribute("products", products);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", page);
        request.getRequestDispatcher("view/product/Product.jsp").forward(request, response);
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
        String name = request.getParameter("name");

        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.searchByName(name);

        if (name.isEmpty()) {
            response.sendRedirect("product");
        } else {
            request.setAttribute("name", name);
            request.setAttribute("products", products);
            request.getRequestDispatcher("view/product/Product.jsp").forward(request, response);
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

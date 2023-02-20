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
import model.Group;
import model.Product;

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
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Group> groups = pdb.getGroups();

        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../view/product/insert.jsp").forward(request, response);
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
        String name = request.getParameter("name").replaceAll("\\s\\s+", " ").trim();
        int im = Integer.parseInt(request.getParameter("priceImport"));
        int ex = Integer.parseInt(request.getParameter("priceExport"));
        int group = Integer.parseInt(request.getParameter("group"));

        if (im < ex) {
            Product p = new Product();
            p.setName(name);
            p.setQuantity(0);
            p.setPriceImport(im);
            p.setPriceExport(ex);
            
            Group g = new Group();
            g.setId(group);
            p.setGroup(g);

            ProductDBContext pdb = new ProductDBContext();
            pdb.insert(p);
            
            String servletPath = request.getContextPath();
            servletPath += "/product";
            response.sendRedirect(servletPath);
        } else {
            ProductDBContext pdb = new ProductDBContext();
            ArrayList<Group> groups = pdb.getGroups();

            request.setAttribute("groups", groups);
            request.setAttribute("name", name);
            request.setAttribute("priceImport", im);
            request.setAttribute("priceExport", ex);
            request.setAttribute("err", "Import price > Export price. Re-enter!");
            request.getRequestDispatcher("../view/product/insert.jsp").forward(request, response);
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

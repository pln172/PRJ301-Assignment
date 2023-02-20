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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

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

        ProductDBContext pdb = new ProductDBContext();
        Product product = pdb.getPro(id);

        request.setAttribute("product", product);
        request.setAttribute("err", request.getParameter("err"));
        request.getRequestDispatcher("../view/product/update.jsp").forward(request, response);
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
        int im = Integer.parseInt(request.getParameter("priceImport"));
        int ex = Integer.parseInt(request.getParameter("priceExport"));

        if (im < ex) {
            ProductDBContext pdb = new ProductDBContext();
            Product pro = pdb.getPro(id);
            pro.setName(request.getParameter("name").replaceAll("\\s\\s+", " ").trim());
            pro.setQuantity(pro.getQuantity());
            pro.setPriceImport(im);
            pro.setPriceExport(ex);

            pdb.update(pro);
            response.sendRedirect("http://localhost:8080/ASSIGNMENT/product");
        } else {
            response.sendRedirect("update?id=" + id + "&err=1");
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

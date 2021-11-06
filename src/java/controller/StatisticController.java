/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.ImportDBContext;
import dal.OrderDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Import;
import model.Order;
import model.Product;

/**
 *
 * @author ASUS
 */
public class StatisticController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.getProducts();
        int inventory = 0;

        for (Product p : products) {
            inventory += p.getQuantity();
        }

        OrderDBContext odb = new OrderDBContext();
        ArrayList<Order> orders = odb.getOrders();
        int revenue = 0;

        for (Order o : orders) {
            revenue += o.getTotal();
        }
        
        ImportDBContext idb = new ImportDBContext();
        ArrayList<Import> imports = idb.getImports();
        int capital = 0;
        
        for (Import i : imports) {
            capital += i.getTotal();
        }

        request.setAttribute("inventory", inventory);
        request.setAttribute("revenue", revenue);
        request.setAttribute("invoice", orders.size());
        request.setAttribute("capital", capital);
        request.setAttribute("interest", revenue-capital);
        

        request.getRequestDispatcher("view/Statistic.jsp").forward(request, response);

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

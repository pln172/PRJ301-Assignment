/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phuon
 */
public class AdminLoginAfterController extends BaseRequiredAuthController {
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("view/Account.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        for (Cookie c : arr) {
            if (c.getName().equals("userC")) {
                request.setAttribute("user", c.getValue());
            }
            if (c.getName().equals("passC")) {
                request.setAttribute("pass", c.getValue());
                request.setAttribute("remember", "yes");
            }
        }

        Account acc = (Account) request.getSession().getAttribute("account");

        if (acc == null) {
            request.getRequestDispatcher("view/Login.jsp").forward(request, response);
        } else {
            response.sendRedirect("statistic");
        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String remember = request.getParameter("remember");
        if (remember == null) {
            remember = "no";
        }

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        AccountDBContext adb = new AccountDBContext();
        Account acc = adb.getAccount(user, pass);

        if (acc != null) {
            request.getSession().setAttribute("account", acc);

            if (remember.equals("yes")) {
                Cookie u = new Cookie("userC", user);
                Cookie p = new Cookie("passC", pass);
                u.setMaxAge(43200);
                p.setMaxAge(43200);
                response.addCookie(u);
                response.addCookie(p);
            } else {
                Cookie arr[] = request.getCookies();
                for (Cookie c : arr) {
                    if (c.getName().equals("userC")) {
                        c.setMaxAge(-1);
                        response.addCookie(c);
                    }
                    if (c.getName().equals("passC")) {
                        request.setAttribute("pass", c.getValue());
                        c.setMaxAge(-1);
                        response.addCookie(c);
                    }
                }
            }

            if (acc.getUsername().equals("loandp")) {
                response.sendRedirect("statistic");
            } else {
                response.sendRedirect("sell");
            }
        } else {
            request.getSession().setAttribute("account", null);
            request.setAttribute("user", user);
            request.setAttribute("pass", pass);
            request.setAttribute("err", "Please check username or password!");
            request.getRequestDispatcher("view/Login.jsp").forward(request, response);
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

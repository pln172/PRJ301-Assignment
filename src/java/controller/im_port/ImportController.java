/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.im_port;

import controller.BaseRequiredAuthController;
import dal.ImportDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Import;
import model.ImportDetail;
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
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Product> products = pdb.getProducts();
        request.setAttribute("products", products);

        request.setAttribute("err", request.getParameter("err"));
        request.getRequestDispatcher("view/import/Import.jsp").forward(request, response);
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
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSS");
        String formattedDate = myDateObj.format(dtf);
        Timestamp ts = Timestamp.valueOf(formattedDate);
        
        Import im = new Import();
        im.setDate(ts);

        String[] pros = request.getParameterValues("pro");
        String[] quantity = request.getParameterValues("quantity");

        ProductDBContext pdb = new ProductDBContext();

        boolean check_dup = true;
        for (int i = 0; i < pros.length - 1; i++) {
            for (int j = i + 1; j < pros.length; j++) {
                if (!pros[i].equals("0")) {
                    if (pros[i].equals(pros[j])) {
                        check_dup = false;
                        break;
                    }
                }
            }
        }

        boolean check_noquan = true;
        for (int i = 0; i < pros.length; i++) {
            if ((pros[i].equals("0") && !quantity[i].equals("0"))) {
                check_noquan = false;
            }
        }
        
        if (check_dup && check_noquan) {
            if (pros != null) {
                for (int i = 0; i < pros.length; i++) {
                    if (Integer.parseInt(pros[i]) != 0 && Integer.parseInt(quantity[i]) != 0) {
                        Product p = pdb.getPro(Integer.parseInt(pros[i]));
                        ImportDetail id = new ImportDetail();
                        id.setIid(im);
                        id.setPid(p);
                        id.setQuantity(Integer.parseInt(quantity[i]));
//                        id.setPrice(p);
                        id.setTotal(p.getPriceImport() * id.getQuantity());
                        im.getImportDetails().add(id);
                        p.setQuantity(p.getQuantity() + id.getQuantity());
                        pdb.update(p);
                    }
                }
            }

            int total = 0;
            for (ImportDetail id : im.getImportDetails()) {
                total += id.getTotal();
            }

            im.setTotal(total);
            ImportDBContext idb = new ImportDBContext();
            idb.insert(im);

            Account acc = (Account) request.getSession().getAttribute("account");
            if (acc.getRight().equals("admin")) {
                response.sendRedirect("http://localhost:8080/ASSIGNMENT/history/import");
            }
        } else if (!check_dup && check_noquan) {
            response.sendRedirect("import?err=1");
        } else if (check_dup && !check_noquan) {
            response.sendRedirect("import?err=2");
        } else if (!check_noquan && !check_dup) {
            response.sendRedirect("import?err=3");
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

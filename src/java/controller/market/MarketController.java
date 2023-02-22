
package controller.market;

import controller.BaseRequiredAuthController;
import dal.MarketDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Market;


public class MarketController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String raw_page = request.getParameter("page");
        if (raw_page == null || raw_page.length() == 0) {
            raw_page = "1";
        }

        int page = Integer.parseInt(raw_page);
        int pageSize = 7;

        MarketDBContext mdb = new MarketDBContext();
        ArrayList<Market> markets = mdb.pagging(pageSize, page);

        int count = mdb.numberRecord();
        int totalPage = (count % pageSize == 0) ? count / pageSize : (count / pageSize) + 1;

        request.setAttribute("markets", markets);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", page);
        
       request.getRequestDispatcher("view/market/Market.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

}
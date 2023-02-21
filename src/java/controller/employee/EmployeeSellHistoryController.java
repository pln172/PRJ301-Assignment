package controller.employee;

import controller.BaseRequiredAuthController;
import dal.EmployeeDBContext;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

public class EmployeeSellHistoryController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDate now = java.time.LocalDate.now();
        Date date = Date.valueOf(now.toString());
        EmployeeDBContext edb = new EmployeeDBContext();
        ArrayList<Order> orders = edb.getOrderByDate(date);

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("../view/employee/sell_history.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

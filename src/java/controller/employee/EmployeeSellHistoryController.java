package controller.employee;

import controller.BaseRequiredAuthController;
import dal.EmployeeDBContext;
import dal.OrderDBContext;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import dal.AccountDBContext;
import model.Account;

public class EmployeeSellHistoryController extends BaseRequiredAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        LocalDate now = java.time.LocalDate.now();
        Date date = Date.valueOf(now.toString());
        EmployeeDBContext edb = new EmployeeDBContext();
        ArrayList<Order> orders = edb.getOrderByDate(date, acc.getEmployee().getId());

        request.setAttribute("orders", orders);
        request.getRequestDispatcher("../view/employee/sell_history.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        OrderDBContext odb = new OrderDBContext();
        Order order = odb.getOrder(id);

        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(gson.toJson(order));
    }

}

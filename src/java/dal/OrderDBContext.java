/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Employee;
import model.Order;
import model.OrderDetail;

/**
 *
 * @author ASUS
 */
public class OrderDBContext extends DBContext {

    public void insert(Order o) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Order]\n"
                    + "           ([eid]\n"
                    + "           ,[cid]\n"
                    + "           ,[date]\n"
                    + "           ,[ototal])\n"
                    + "     VALUES(\n"
                    + "           ?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, o.getEid().getId());
            stm.setInt(2, o.getCid().getId());
            stm.setTimestamp(3, o.getDate());
            stm.setInt(4, o.getTotal());
            stm.executeUpdate();

            String sql_get_oid = "select @@identity as oid";
            PreparedStatement stm_get_oid = connection.prepareStatement(sql_get_oid);
            ResultSet rs = stm_get_oid.executeQuery();

            if (rs.next()) {
                o.setId(rs.getInt("oid"));
            }

            for (OrderDetail od : o.getOrderDetails()) {
                String sql_add_detail = "INSERT INTO [OrderDetails]\n"
                        + "           ([oid]\n"
                        + "           ,[pid]\n"
                        + "           ,[quantity]\n"
                        + "           ,[price]\n"
                        + "           ,[total])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";

                PreparedStatement stm_add_detail = connection.prepareStatement(sql_add_detail);
                stm_add_detail.setInt(1, od.getOid().getId());
                stm_add_detail.setInt(2, od.getPid().getId());
                stm_add_detail.setInt(3, od.getQuantity());
                stm_add_detail.setInt(4, od.getPid().getPriceExport());
                stm_add_detail.setInt(5, od.getTotal());

                stm_add_detail.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Order getOrder(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[orderNo]\n"
                    + "      ,[eid]\n"
                    + "      ,[cid]\n"
                    + "      ,[date]\n"
                    + "      ,[ototal]\n"
                    + "  FROM [Order]\n"
                    + "  where id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));

                Employee e = new Employee();
                e.setId(rs.getInt("eid"));
                o.setEid(e);

                Customer c = new Customer();
                c.setId(rs.getInt("cid"));
                o.setCid(c);

                o.setDate(rs.getTimestamp("date"));
                o.setTotal(rs.getInt("ototal"));
                return o;
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Order getOrderDetail() {
        int id = 0;
        try {
            String sql = "select @@identity as oid";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                id = rs.getInt("oid");
            }

            String sql_search = "select orderNo, date, ototal, Employee.ename, Customer.cname, Product.pname, \n"
                    + "		OrderDetails.quantity, price, total\n"
                    + "from [Order]\n"
                    + "	inner join Employee on [Order].eid = Employee.id\n"
                    + "	inner join Customer on [Order].cid = Customer.id\n"
                    + "	inner join OrderDetails on [Order].id = OrderDetails.oid\n"
                    + "	inner join Product on OrderDetails.pid = Product.id\n"
                    + "where [Order].id = ?";
            PreparedStatement stm_search = connection.prepareStatement(sql_search);
            stm_search.setInt(1, id);
            ResultSet rs_search = stm_search.executeQuery();
            
            while (rs_search.next()) {
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

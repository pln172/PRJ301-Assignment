/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Employee;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author ASUS
 */
public class OrderDBContext extends DBContext {

    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "	  ,[orderNo]\n"
                    + "      ,[date]\n"
                    + "      ,[ototal]\n"
                    + "  FROM [Order]\n"
                    + "  ORDER BY date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setDate(rs.getTimestamp("date"));
                o.setTotal(rs.getInt("ototal"));

                orders.add(o);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public ArrayList<Order> searchByDate(Date date) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "	  ,[orderNo]\n"
                    + "      ,[date]\n"
                    + "      ,[ototal]\n"
                    + "  FROM [Order]\n"
                    + "  WHERE Cast([date] as date) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setDate(rs.getTimestamp("date"));
                o.setTotal(rs.getInt("ototal"));

                orders.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

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
            if (o.getEid().getId() != -1) {
                stm.setInt(1, o.getEid().getId());
            } else {
                stm.setNull(1, Types.NULL);
            }
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
            String sql = "select [Order].id, orderNo, [date], ototal, \n"
                    + "		Employee.ename, Customer.cname,\n"
                    + "		Product.pname,\n"
                    + "		OrderDetails.quantity, price, total\n"
                    + "from [Order]\n"
                    + "	left join Employee on [Order].eid = Employee.id\n"
                    + "	inner join Customer on [Order].cid = Customer.id\n"
                    + "	inner join OrderDetails on [Order].id = OrderDetails.oid\n"
                    + "	inner join Product on OrderDetails.pid = Product.id\n"
                    + "where [Order].id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Order o = new Order();
            while (rs.next()) {
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setDate(rs.getTimestamp("date"));
                o.setTotal(rs.getInt("ototal"));

                Employee e = new Employee();
                e.setName(rs.getString("ename"));
                o.setEid(e);

                Customer c = new Customer();
                c.setName(rs.getString("cname"));
                o.setCid(c);

                Product p = new Product();
                p.setName(rs.getString("pname"));
                p.setPriceExport(rs.getInt("price"));

                OrderDetail od = new OrderDetail();
                od.setPid(p);
                od.setQuantity(rs.getInt("quantity"));
                od.setPrice(p);
                od.setTotal(rs.getInt("total"));

                o.getOrderDetails().add(od);
            }
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Order getOrderDetail() {
        int id = 0;
        try {
            String sql = "select id from [Order] where id >= all (select id from [Order])";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }

            String sql_search = "select orderNo, date, ototal, Employee.ename, Customer.cname, Product.pname, \n"
                    + "		OrderDetails.pid, OrderDetails.quantity, price, total\n"
                    + "from [Order]\n"
                    + "	inner join Employee on [Order].eid = Employee.id\n"
                    + "	inner join Customer on [Order].cid = Customer.id\n"
                    + "	inner join OrderDetails on [Order].id = OrderDetails.oid\n"
                    + "	inner join Product on OrderDetails.pid = Product.id"
                    + "where [Order].id = ?";
            PreparedStatement stm_search = connection.prepareStatement(sql_search);
            stm_search.setInt(1, id);
            ResultSet rs_search = stm_search.executeQuery();

            while (rs_search.next()) {
                Order o = new Order();
                o.setId(id);
                o.setOrderNo(rs_search.getString("orderNo"));
                o.setDate(rs_search.getTimestamp("date"));
                o.setTotal(rs_search.getInt("ototal"));

                Employee e = new Employee();
                e.setName(rs_search.getString("ename"));
                o.setEid(e);

                Customer c = new Customer();
                c.setName(rs_search.getString("cname"));
                o.setCid(c);

                Product p = new Product();
                p.setId(rs_search.getInt("pid"));
                p.setName(rs_search.getString("pname"));
                p.setPriceExport(rs_search.getInt("price"));

                OrderDetail od = new OrderDetail();
                od.setPid(p);
                od.setQuantity(rs_search.getInt("quantity"));
                od.setTotal(rs_search.getInt("total"));

                o.getOrderDetails().add(od);
                return o;

            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void delete(int id) {
        OrderDetailDBContext oddb = new OrderDetailDBContext();
        oddb.delete(id);

        try {
            String sql = "DELETE FROM [Order]\n"
                    + "      WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(id) as NumberOfRecord\n"
                    + "from [Order]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("NumberOfRecord");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public ArrayList<Order> pagging(int pagesize, int pageindex) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "WITH Ord AS (\n"
                    + "     SELECT id, orderNo, date, ototal,\n"
                    + "     ROW_NUMBER() OVER (ORDER BY id) AS 'RowNumber'\n"
                    + "     FROM [Order]\n"
                    + ") \n"
                    + " SELECT id, orderNo, date, ototal\n"
                    + " FROM Ord\n"
                    + "WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?\n"
                    + "ORDER BY date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setOrderNo(rs.getString("orderNo"));
                o.setDate(rs.getTimestamp("date"));
                o.setTotal(rs.getInt("ototal"));

                orders.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}

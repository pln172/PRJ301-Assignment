/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author ASUS
 */
public class CustomerDBContext extends DBContext {

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[customerNo]\n"
                    + "      ,[cname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "  FROM [Customer]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomerNo(rs.getString("customerNo"));
                c.setName(rs.getString("cname"));
                c.setGender(rs.getBoolean("gender"));
                c.setDob(rs.getDate("dob"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));

                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }
    
    public ArrayList<Customer> searchByName(String name) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[customerNo]\n"
                    + "      ,[cname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "  FROM [Customer]"
                    + "WHERE cname like '%' + ? + '%'";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomerNo(rs.getString("customerNo"));
                c.setName(rs.getString("cname"));
                c.setGender(rs.getBoolean("gender"));
                c.setDob(rs.getDate("dob"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));

                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    public Customer getCus(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[customerNo]\n"
                    + "      ,[cname]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "  FROM [Customer]"
                    + "  WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setCustomerNo(rs.getString("customerNo"));
                c.setName(rs.getString("cname"));
                c.setGender(rs.getBoolean("gender"));
                c.setDob(rs.getDate("dob"));
                c.setPhone(rs.getString("phone"));
                c.setEmail(rs.getString("email"));
                c.setAddress(rs.getString("address"));

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void insert(Customer c) {
        try {
            String sql = "INSERT INTO [Customer]\n"
                    + "           ([cname]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[phone]\n"
                    + "           ,[email]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setBoolean(2, c.isGender());
            stm.setDate(3, c.getDob());
            stm.setString(4, c.getPhone());
            stm.setString(5, c.getEmail());
            stm.setString(6, c.getAddress());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Customer c) {
        try {
            String sql = "UPDATE [Customer]\n"
                    + "   SET [cname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[phone] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getName());
            stm.setBoolean(2, c.isGender());
            stm.setDate(3, c.getDob());
            stm.setString(4, c.getPhone());
            stm.setString(5, c.getEmail());
            stm.setString(6, c.getAddress());
            stm.setInt(7, c.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM [Customer]\n"
                    + "      WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

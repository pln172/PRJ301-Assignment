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
import model.Employee;

/**
 *
 * @author ASUS
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "select id, [ename], gender, dob, \n"
                    + "phone, email, [address], active\n"
                    + "from Employee";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ename"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));
                e.setAddress(rs.getString("address"));
                e.setActive(rs.getBoolean("active"));

                employees.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public Employee getEmployee(int id) {
        try {
            String sql = "select id, [ename], gender, dob, \n"
                    + "phone, email, [address], active\n"
                    + "from Employee "
                    + "where id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("ename"));
                e.setGender(rs.getBoolean("gender"));
                e.setDob(rs.getDate("dob"));
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));
                e.setAddress(rs.getString("address"));
                e.setActive(rs.getBoolean("active"));

                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void insert(Employee e) {
        try {
            String sql = "INSERT INTO [Employee]\n"
                    + "           ([ename]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[phone]\n"
                    + "           ,[email]\n"
                    + "           ,[address]\n"
                    + "           ,[active])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getName());
            stm.setBoolean(2, e.isGender());
            stm.setDate(3, e.getDob());
            stm.setString(4, e.getPhone());
            stm.setString(5, e.getEmail());
            stm.setString(6, e.getAddress());
            stm.setBoolean(7, e.isActive());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(Employee e) {
        try {
            String sql = "UPDATE [Employee]\n"
                    + "   SET [ename] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[phone] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[address] = ?\n"
                    + "      ,[active] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getName());
            stm.setBoolean(2, e.isGender());
            stm.setDate(3, e.getDob());
            stm.setString(4, e.getPhone());
            stm.setString(5, e.getEmail());
            stm.setString(6, e.getAddress());
            stm.setBoolean(7, e.isActive());
            stm.setInt(8, e.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM [Employee]\n"
                    + "      WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

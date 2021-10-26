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
import model.Employee;

/**
 *
 * @author ASUS
 */
public class EmployeeDBContext extends DBContext {

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "SELECT [id]\n"
                    + "      ,[ename]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "      ,[starting_date]\n"
                    + "      ,[leaving_date]\n"
                    + "      ,[active]\n"
                    + "  FROM [Employee]";
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
                e.setStarting_date(rs.getDate("starting_date"));
                e.setLeaving_date(rs.getDate("leaving_date"));
                e.setActive(rs.getBoolean("active"));

                employees.add(e);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public ArrayList<Employee> searchByName(String name) {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "SELECT [id]\n"
                    + "      ,[ename]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "      ,[starting_date]\n"
                    + "      ,[leaving_date]\n"
                    + "      ,[active]\n"
                    + "  FROM [Employee]\n"
                    + "WHERE ename like '%' + ? + '%'";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
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
                e.setStarting_date(rs.getDate("starting_date"));
                e.setLeaving_date(rs.getDate("leaving_date"));
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
            String sql = "SELECT [id]\n"
                    + "      ,[ename]\n"
                    + "      ,[gender]\n"
                    + "      ,[dob]\n"
                    + "      ,[phone]\n"
                    + "      ,[email]\n"
                    + "      ,[address]\n"
                    + "      ,[starting_date]\n"
                    + "      ,[leaving_date]\n"
                    + "      ,[active]\n"
                    + "  FROM [Employee]"
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
                e.setStarting_date(rs.getDate("starting_date"));
                e.setLeaving_date(rs.getDate("leaving_date"));
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
                    + "           ,[starting_date]\n"
                    + "           ,[active])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
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
            stm.setDate(7, e.getStarting_date());
            stm.setBoolean(8, e.isActive());
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
                    + "      ,[leaving_date] = ?\n"
                    + "      ,[active] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getName());
            stm.setBoolean(2, e.isGender());
            stm.setDate(3, e.getDob());
            stm.setString(4, e.getPhone());
            stm.setString(5, e.getEmail());
            stm.setString(6, e.getAddress());
            if (e.isActive()) {
                stm.setNull(7, Types.NULL);
            } else {
                stm.setDate(7, e.getLeaving_date());
            }
            stm.setBoolean(8, e.isActive());
            stm.setInt(9, e.getId());
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

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(id) as NumberOfRecord\n"
                    + "from Employee";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("NumberOfRecord");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public ArrayList<Employee> pagging(int pagesize, int pageindex) {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "WITH Emp AS (\n"
                    + "     SELECT id, ename, [gender],\n"
                    + "     [dob], [phone], [email], [address],\n"
                    + "	 [starting_date], [leaving_date], [active],\n"
                    + "     ROW_NUMBER() OVER (ORDER BY id) AS 'RowNumber'\n"
                    + "     FROM Employee\n"
                    + ") \n"
                    + " SELECT id, ename, [gender],\n"
                    + " [dob], [phone], [email], [address],\n"
                    + " [starting_date], [leaving_date], [active]\n"
                    + " FROM Emp\n"
                    + "WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
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
                e.setStarting_date(rs.getDate("starting_date"));
                e.setLeaving_date(rs.getDate("leaving_date"));
                e.setActive(rs.getBoolean("active"));

                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }
}

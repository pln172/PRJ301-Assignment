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
import model.Account;
import model.Employee;
import model.Feature;

/**
 *
 * @author ASUS
 */
public class AccountDBContext extends DBContext {

    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[password]\n"
                    + "      ,[right]\n"
                    + "  FROM [Account]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));

                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public ArrayList<Feature> getFeatures(String right) {
        ArrayList<Feature> features = new ArrayList<>();
        try {
            String sql = "SELECT af.[fid]\n"
                    + "       ,f.url\n"
                    + "  FROM [AccountFeature] af\n"
                    + "  INNER JOIN Feature f\n"
                    + "  ON f.fid = af.fid"
                    + "  WHERE [right] like ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, right);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Feature f = new Feature();
                f.setUrl(rs.getString("url"));
                features.add(f);
            }
            return features;
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccount(String user, String pass) {
        try {
            String sql = "SELECT a.username, a.password, a.employeeid, a.[right]\n"
                    + "     FROM Account a\n"
                    + "  WHERE BINARY_CHECKSUM(a.username) = BINARY_CHECKSUM(?)\n"
                    + "      AND BINARY_CHECKSUM(a.password) = BINARY_CHECKSUM(?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();

            Account acc = null;
            while (rs.next()) {
                if (acc == null) {
                    acc = new Account();
                    acc.setUsername(user);
                    acc.setPassword(pass);
                    acc.setRight(rs.getString("right"));
                }

                Employee e = new Employee();
                e.setId(rs.getInt("employeeid"));
                acc.setEmployee(e);

//                int fid = rs.getInt("fid");
//                if (fid != 0) {
//                    Feature f = new Feature();
//                    f.setId(fid);
//                    f.setUrl(rs.getString("url"));
//                    acc.getFeatures().add(f);
//                }
            }

            return acc;

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Account a) {
        try {
            int id = 0;
            String sql_search = "SELECT [id]\n"
                    + "  FROM [Employee]\n"
                    + "  where id >= all (SELECT [id] FROM [Employee])";

            PreparedStatement stm_search = connection.prepareStatement(sql_search);
            ResultSet rs_search = stm_search.executeQuery();
            if (rs_search.next()) {
                id = rs_search.getInt("id");
            }

            String sql = "INSERT INTO [Account]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[employeeid]\n"
                    + "           ,[right])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUsername());
            stm.setString(2, a.getPassword());
            stm.setInt(3, id);
            stm.setString(4, "emp");
            stm.executeUpdate();

//            for (int i = 1; i <= 5; i++) {
//                String sql_feature = "INSERT INTO [AccountFeature]\n"
//                        + "           ([username]\n"
//                        + "           ,[fid])\n"
//                        + "     VALUES\n"
//                        + "           (?\n"
//                        + "           ,?)";
//                PreparedStatement stm_feature = connection.prepareStatement(sql_feature);
//                stm_feature.setString(1, a.getUsername());
//                stm_feature.setInt(2, i);
//                stm_feature.executeUpdate();
//            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getAccountEmp(int empId) {
        try {
            String sql = "SELECT [username]\n"
                    + "      ,[password]\n"
                    + "      ,[employeeid]\n"
                    + "  FROM [Account]\n"
                    + "  where employeeid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, empId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));

                Employee e = new Employee();
                e.setId(rs.getInt("employeeid"));
                acc.setEmployee(e);

                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Account a) {
        try {
            String sql = "UPDATE [Account]\n"
                    + "   SET [password] = ?\n"
                    + " WHERE username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getPassword());
            stm.setString(2, a.getUsername());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(Account a) {
        try {
//            String sql_accfeture = "DELETE FROM [AccountFeature]\n"
//                    + "      WHERE username = ?";
//            PreparedStatement stm_accfeature = connection.prepareStatement(sql_accfeture);
//            stm_accfeature.setString(1, a.getUsername());
//            stm_accfeature.executeUpdate();

            String sql_account = "DELETE FROM [Account]\n"
                    + "      WHERE username = ?";
            PreparedStatement stm_account = connection.prepareStatement(sql_account);
            stm_account.setString(1, a.getUsername());
            stm_account.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

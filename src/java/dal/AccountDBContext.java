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
import model.Account;
import model.Employee;
import model.Feature;

/**
 *
 * @author ASUS
 */
public class AccountDBContext extends DBContext {

    public Account getAccount(String user, String pass) {
        try {
            String sql = "SELECT a.username, a.password, a.employeeid, f.fid, f.url\n"
                    + "     FROM Account a LEFT JOIN AccountFeature af\n"
                    + "     ON a.username = af.username\n"
                    + "     LEFT JOIN [Feature] f ON f.fid = af.fid\n"
                    + "  WHERE a.username = ? AND [password] = ?";

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
                }

                Employee e = new Employee();
                e.setId(rs.getInt("employeeid"));
                acc.setEmployee(e);

                int fid = rs.getInt("fid");
                if (fid != 0) {
                    Feature f = new Feature();
                    f.setId(fid);
                    f.setUrl(rs.getString("url"));
                    acc.getFeatures().add(f);
                }
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
                    + "           ,[employeeid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUsername());
            stm.setString(2, a.getPassword());
            stm.setInt(3, id);
            stm.executeUpdate();

            for (int i = 1; i <= 3; i++) {
                String sql_feature = "INSERT INTO [AccountFeature]\n"
                        + "           ([username]\n"
                        + "           ,[fid])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?)";
                PreparedStatement stm_feature = connection.prepareStatement(sql_feature);
                stm_feature.setString(1, a.getUsername());
                stm_feature.setInt(2, i);
                stm_feature.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

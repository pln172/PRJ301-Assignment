/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Market;

/**
 *
 * @author phuon
 */
public class MarketDBContext extends DBContext {

    public Connection connection;

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(id) as NumberOfRecord\n"
                    + "from Market";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("NumberOfRecord");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public ArrayList<Market> pagging(int pagesize, int pageindex) {
        ArrayList<Market> markets = new ArrayList<>();
        try {
            String sql = "WITH Mar AS ( \n"
                    + "                          SELECT [id],[name],[date],[total],[note],\n"
                    + "                          ROW_NUMBER() OVER (ORDER BY id) AS 'RowNumber' \n"
                    + "                          FROM Market)  \n"
                    + "                      SELECT [id],[name],[date],[total],[note]\n"
                    + "                      FROM Mar \n"
                    + "                     WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Market m = new Market();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setDate(rs.getDate("date"));
                m.setId(rs.getInt("total"));
                m.setNote(rs.getString("note"));

                markets.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return markets;
    }

    public void insert(Market m) {
        try {
            String sql = "INSERT INTO [Market]\n"
                    + "           ([name]\n"
                    + "           ,[date]\n"
                    + "           ,[total]\n"
                    + "           ,[note])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            int i = 1;
            stm.setString(i++, m.getName());
            stm.setDate(i++, m.getDate());
            stm.setInt(i++, m.getTotal());
            stm.setString(i++, m.getNote());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Market m) {
        try {
            String sql = "UPDATE [Market]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[date] = ?\n"
                    + "      ,[total] = ?\n"
                    + "      ,[note] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            int i = 1;
            stm.setString(i++, m.getName());
            stm.setDate(i++, m.getDate());
            stm.setInt(i++, m.getTotal());
            stm.setString(i++, m.getNote());
            stm.setInt(i++, m.getId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MarketDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

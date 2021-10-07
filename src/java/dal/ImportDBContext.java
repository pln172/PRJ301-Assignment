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
import model.Import;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ImportDBContext extends DBContext {

    public ArrayList<Import> getiImports() {
        ArrayList<Import> imports = new ArrayList<>();

        try {
            String sql = "SELECT [pid], Product.productNo, Product.[name], [date], Import.[quantity]\n"
                    + "  FROM [Import] inner join Product \n"
                    + "  ON Import.pid = Product.id"
                    + "  ORDER BY date desc";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("name"));

                Import i = new Import();
                i.setPid(p);
                i.setDate(rs.getTimestamp("date"));
                i.setQuantity(rs.getInt("quantity"));
                imports.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imports;
    }

    public void insert(Import i) {
        try {
            String sql = "INSERT INTO [Import]\n"
                    + "           ([pid]\n"
                    + "           ,[date]\n"
                    + "           ,[quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i.getPid().getId());
            stm.setTimestamp(2, i.getDate());
            stm.setInt(3, i.getQuantity());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

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
import model.Product;

/**
 *
 * @author ASUS
 */
public class ProductDBContext extends DBContext {

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[productNo]\n"
                    + "      ,[pname]\n"
                    + "      ,[quantity]\n"
                    + "      ,[priceImport]\n"
                    + "      ,[priceExport]\n"
                    + "  FROM [Product]";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("pname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPriceImport(rs.getInt("priceImport"));
                p.setPriceExport(rs.getInt("priceExport"));

                products.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product getPro(int id) {
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[productNo]\n"
                    + "      ,[pname]\n"
                    + "      ,[quantity]\n"
                    + "      ,[priceImport]\n"
                    + "      ,[priceExport]\n"
                    + "  FROM [Product]"
                    + "  WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("pname"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPriceImport(rs.getInt("priceImport"));
                p.setPriceExport(rs.getInt("priceExport"));

                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Product p) {
        try {
            String sql = "INSERT INTO [Product]\n"
                    + "           ([pname]\n"
                    + "           ,[quantity]\n"
                    + "           ,[priceImport]\n"
                    + "           ,[priceExport])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setInt(2, p.getQuantity());
            stm.setInt(3, p.getPriceImport());
            stm.setInt(4, p.getPriceExport());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Product p) {
        try {
            String sql = "UPDATE [Product]\n"
                    + "   SET [pname] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[priceImport] = ?\n"
                    + "      ,[priceExport] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setInt(2, p.getQuantity());
            stm.setInt(3, p.getPriceImport());
            stm.setInt(4, p.getPriceExport());
            stm.setInt(5, p.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM [Product]\n"
                    + "      WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

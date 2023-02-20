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
import model.Group;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ProductDBContext extends DBContext {

    public ArrayList<Group> getGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[gname]\n"
                    + "  FROM [Group]";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()) {
                Group g = new Group();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("gname"));
                
                groups.add(g);
            }
            
            return groups;
        } catch (Exception e) {
        }
        return null;
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[productNo]\n"
                    + "      ,[pname]\n"
                    + "      ,[quantity]\n"
                    + "      ,[priceImport]\n"
                    + "      ,[priceExport]\n"
                    + "      ,[gid]\n"
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

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                
                p.setGroup(g);

                products.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public ArrayList<Product> searchByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[productNo]\n"
                    + "      ,[pname]\n"
                    + "      ,[quantity]\n"
                    + "      ,[priceImport]\n"
                    + "      ,[priceExport]\n"
                    + "  FROM [Product]"
                    + "WHERE pname like '%' + ? + '%'";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
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
                    + "      ,[gid]\n"
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

                Group g = new Group();
                g.setId(rs.getInt("gid"));
                
                p.setGroup(g);
                
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
                    + "           ,[priceExport]\n"
                    + "           ,[gid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setInt(2, p.getQuantity());
            stm.setInt(3, p.getPriceImport());
            stm.setInt(4, p.getPriceExport());
            stm.setInt(5, p.getGroup().getId());

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
                    + "      ,[gid] = ?\n"
                    + " WHERE id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, p.getName());
            stm.setInt(2, p.getQuantity());
            stm.setInt(3, p.getPriceImport());
            stm.setInt(4, p.getPriceExport());
            stm.setInt(5, p.getGroup().getId());
            stm.setInt(6, p.getId());
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

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(id) as NumberOfRecord\n"
                    + "from Product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("NumberOfRecord");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public ArrayList<Product> pagging(int pagesize, int pageindex) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = "WITH Pro AS (\n"
                    + "     SELECT id, [productNo], [pname],\n"
                    + "     [quantity], [priceImport], [priceExport],\n"
                    + "     ROW_NUMBER() OVER (ORDER BY id) AS 'RowNumber'\n"
                    + "     FROM Product\n"
                    + ") \n"
                    + " SELECT id, [productNo], [pname],\n"
                    + "     [quantity], [priceImport], [priceExport]\n"
                    + " FROM Pro\n"
                    + "WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
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
}

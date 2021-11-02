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

    public ArrayList<Import> getImports() {
        ArrayList<Import> imports = new ArrayList<>();

        try {
            String sql = "SELECT [pid], Product.productNo, Product.[pname], [date]\n"
                    + ",priceImport, Import.[quantity]\n"
                    + "  FROM [Import] inner join Product\n"
                    + "  ON Import.pid = Product.id\n"
                    + "  ORDER BY date desc";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("pname"));
                p.setPriceImport(rs.getInt("priceImport"));

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

    public ArrayList<Import> searchByDate(Date date) {
        ArrayList<Import> imports = new ArrayList<>();
        try {
            String sql = "SELECT [pid], Product.productNo, Product.[pname], [date]\n"
                    + ",priceImport, Import.[quantity]\n"
                    + "  FROM [Import] inner join Product\n"
                    + "  ON Import.pid = Product.id\n"
                    + "  WHERE Cast([date] as date) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("pname"));
                p.setPriceImport(rs.getInt("priceImport"));

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

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(pid) as NumberOfRecord\n"
                    + "from Import";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                num = rs.getInt("NumberOfRecord");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return num;
    }

    public ArrayList<Import> pagging(int pagesize, int pageindex) {
        ArrayList<Import> imports = new ArrayList<>();
        try {
            String sql = "WITH Imp AS (\n"
                    + "     SELECT pid, Product.[productNo], Product.[pname],\n"
                    + "     [date], [priceImport], Import.[quantity],\n"
                    + "     ROW_NUMBER() OVER (ORDER BY pid) AS 'RowNumber'\n"
                    + "     FROM Import inner join Product\n"
                    + "	 ON Import.pid = Product.id\n"
                    + ") \n"
                    + " SELECT pid, [productNo], [pname],\n"
                    + "     [date], [priceImport], [quantity]\n"
                    + " FROM Imp\n"
                    + "WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?\n"
                    + " ORDER BY date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setProductNo(rs.getString("productNo"));
                p.setName(rs.getString("pname"));
                p.setPriceImport(rs.getInt("priceImport"));

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

    public int getCapital(Date from, Date to) {
        int capital = 0;
        try {
            String sql = "SELECT sum(Import.[quantity] * Product.priceImport) as Capital\n"
                    + "  FROM [Import] inner join Product \n"
                    + "  on Import.pid = Product.id\n"
                    + "  WHERE Cast([date] as date) between ? and ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                capital = rs.getInt("Capital");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return capital;
    }
}

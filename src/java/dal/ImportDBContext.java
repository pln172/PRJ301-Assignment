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
import model.ImportDetail;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ImportDBContext extends DBContext {

    public ArrayList<Import> getImports() {
        ArrayList<Import> imports = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[importNo]\n"
                    + "      ,[date]\n"
                    + "      ,[itotal]\n"
                    + "  FROM [Import]"
                    + "  ORDER BY date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Import i = new Import();
                i.setId(rs.getInt("id"));
                i.setImportNo(rs.getString("importNo"));
                i.setDate(rs.getTimestamp("date"));
                i.setTotal(rs.getInt("itotal"));

                imports.add(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imports;
    }

    public ArrayList<Import> searchByDate(Date date) {
        ArrayList<Import> imports = new ArrayList<>();
        try {
            String sql = "SELECT [id]\n"
                    + "      ,[importNo]\n"
                    + "      ,[date]\n"
                    + "      ,[itotal]\n"
                    + "  FROM [Import]"
                    + "  WHERE Cast([date] as date) = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Import i = new Import();
                i.setId(rs.getInt("id"));
                i.setImportNo(rs.getString("importNo"));
                i.setDate(rs.getTimestamp("date"));
                i.setTotal(rs.getInt("itotal"));

                imports.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imports;
    }

    public void insert(Import i) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Import]\n"
                    + "           ([date]\n"
                    + "           ,[itotal])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setTimestamp(1, i.getDate());
            stm.setInt(2, i.getTotal());
            stm.executeUpdate();

            String sql_get_iid = "select @@identity as iid";
            PreparedStatement stm_get_iid = connection.prepareStatement(sql_get_iid);
            ResultSet rs = stm_get_iid.executeQuery();

            if (rs.next()) {
                i.setId(rs.getInt("iid"));
            }

            for (ImportDetail id : i.getImportDetails()) {
                String sql_add_detail = "INSERT INTO [ImportDetails]\n"
                        + "           ([iid]\n"
                        + "           ,[pid]\n"
                        + "           ,[quantity]\n"
                        + "           ,[price]\n"
                        + "           ,[total])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,?)";

                PreparedStatement stm_add_detail = connection.prepareStatement(sql_add_detail);
                stm_add_detail.setInt(1, i.getId());
                stm_add_detail.setInt(2, id.getPid().getId());
                stm_add_detail.setInt(3, id.getQuantity());
                stm_add_detail.setInt(4, id.getPid().getPriceImport());
                stm_add_detail.setInt(5, id.getTotal());

                stm_add_detail.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Import getImport(int id) {
        try {
            String sql = "select Import.id, importNo, [date], itotal, \n"
                    + "     Product.pname,\n"
                    + "     ImportDetails.quantity, price, total\n"
                    + "     from Import\n"
                    + "     inner join ImportDetails on Import.id = ImportDetails.iid\n"
                    + "     inner join Product on ImportDetails.pid = Product.id\n"
                    + "     where Import.id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Import i = new Import();

            while (rs.next()) {
                i.setId(rs.getInt("id"));
                i.setImportNo(rs.getString("importNo"));
                i.setDate(rs.getTimestamp("date"));
                i.setTotal(rs.getInt("itotal"));

                Product p = new Product();
                p.setName(rs.getString("pname"));
                p.setPriceImport(rs.getInt("price"));

                ImportDetail idl = new ImportDetail();
                idl.setPid(p);
                idl.setQuantity(rs.getInt("quantity"));
                idl.setPrice(p);
                idl.setTotal(rs.getInt("total"));

                i.getImportDetails().add(idl);
            }
            return i;
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int numberRecord() {
        int num = 0;
        try {
            String sql = "select count(id) as NumberOfRecord\n"
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
                    + "              SELECT id, importNo, date, itotal,\n"
                    + "              ROW_NUMBER() OVER (ORDER BY id) AS 'RowNumber'\n"
                    + "              FROM Import\n"
                    + "             ) \n"
                    + "     SELECT id, importNo, date, itotal\n"
                    + "     FROM Imp\n"
                    + "     WHERE RowNumber >= (? - 1)*? + 1 AND RowNumber <= ? * ?\n";
//                    + "     ORDER BY date desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Import i = new Import();
                i.setId(rs.getInt("id"));
                i.setImportNo(rs.getString("importNo"));
                i.setDate(rs.getTimestamp("date"));
                i.setTotal(rs.getInt("itotal"));

                imports.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imports;
    }


    public int getCapital(Date from, Date to) {
        int total = 0;
        try {
            String sql = "SELECT sum([itotal]) as Total\n"
                    + "  FROM [Import]\n"
                    + "  WHERE Cast([date] as date) between ? and ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ImportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}

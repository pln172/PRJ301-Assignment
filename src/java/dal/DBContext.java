/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DBContext {

    protected Connection connection;

    public DBContext() {
        try {
            //            String user = "sa";
//            String pass = "sa";
//            String url = "jdbc:sqlserver://PHOENIX\\SQLEXPRESS:1433;databaseName=CafeGocPho";

            FileReader fr;
            Scanner sc;
            String line;
            String[] info;
            String user = "";
            String pass = "";
            String url = "";

            
//                fr = new FileReader("DBContext.txt");
                InputStream rs = this.getClass().getClassLoader().getResourceAsStream("DBContext.txt");
                sc = new Scanner(rs);

                while (sc.hasNext()) {
                    line = sc.nextLine().trim();

                    if (!line.isEmpty()) {
                        info = line.split(" ");
                        user = info[0].trim();
                        pass = info[1].trim();
                        url = info[2].trim();
                    }
                }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

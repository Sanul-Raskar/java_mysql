/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mysql;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanul
 */
public class Java_mysql {

    public static Connection createConnection() {
        Connection con = null;
        String URL = "jdbc:mysql://localhost:3306/student";
        String user = "sanul";
        String password = "sanul123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, user, password);
            System.out.println("Connected to MySQL");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Java_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

    public static void main(String[] args) {
        
        //Connection n = createConnection();
        Home h = new Home();
        h.setVisible(true);
      
    }

}

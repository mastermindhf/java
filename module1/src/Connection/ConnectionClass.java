/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ConnectionClass {
    public Connection connection;
    public Connection getConnection()
    {
       String url="jdbc:mysql://localhost/final";
        String dbName="esprit";
         String password="";
        String userName="root";
        try {
          Class.forName("com.mysql.jdbc.Driver");
                connection=DriverManager.getConnection(url,userName,password);
                System.out.println("conect etabli");
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        } 

                
    return connection;
}

}
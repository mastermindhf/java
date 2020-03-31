/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author farou
 */
public class DateSource {
     private static DateSource instance;
    private static final String url = "jdbc:mysql://localhost:3306/java";
    private static final String username = "root";
    private static final String password = "";
    Connection connect;

    
    private DateSource(){
        try{
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        }catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
     
    
    public Connection getConnection()
    {
        return connect;
    }
    
    public static DateSource getInstance(){
        if(instance ==null)
            instance = new DateSource();
        
        return instance;
    }
    
}

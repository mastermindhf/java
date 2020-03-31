/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author ASUS
 */
public class Cnx {
    
    String login = "root", mdp = "", url = "jdbc:mysql://localhost:3306/pi";
    static Cnx instance;
    Connection cnx;

    private Cnx() {
        try {
            cnx = DriverManager.getConnection(url, login, mdp);
        } catch (SQLException ex) {
            System.out.println("Not Connected !");
        }
    }

    public Connection getConnection() {
        return cnx;
    }

    public static Cnx getInstance() {
        if (instance == null) {
            instance = new Cnx();
        }
        return instance;

    }

}

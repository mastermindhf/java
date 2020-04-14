/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author farou
 */
public class MyServices {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
     public MyServices(){
    cnx = DataSource.getInstance().getCnx();
    }
     public void ajouterUtilisateurs(User u) {
        try {
            System.out.println(u.toString());
            String requete = "INSERT INTO user (username,username_canonical,email,email_canonical,enabled,password,"
                    + "roles,nom,prenom,phone) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, u.getUsername());
            pst.setString(2, u.getUsername());
            pst.setString(3, u.getEmail());
            pst.setString(4, u.getEmail());
            pst.setInt(5, u.getEnabled());
            pst.setString(6, u.getPassword());
            pst.setString(7, u.getRoles());
            pst.setString(8, u.getNom());
            pst.setString(9, u.getPrenom());
            pst.setString(10, u.getPhone());
      

            pst.executeUpdate();
            System.out.println("Insertion effectué avec succés");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     public Boolean verifierpassword(String pword, String uname) {
        String s1 = "";
        String req = "Select password from user where username= ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement =  cnx.prepareStatement(req);
            preparedStatement.setString(1, uname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString(1);
               // String s2=uname+"{"+s1+"}";
 
                  
              
                System.out.println("ili 3malnelou deccryptage==>"+Password.checkPassword(pword,s1));
                  //   System.out.println(uname);
                   System.out.println(s1);
                 
         
                if ( (Password.checkPassword(pword,s1))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }
      public boolean chercherUtilisateurBylogin(String s) {
        User user = null;
        String req = "select * from user where username =?";
        PreparedStatement preparedStatement;
        try {
         preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getInt("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone")
                         
                  );
                      
                  
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return false;
        }
        return true;
    }
      
       public String Gettype(String s) {
        String s1 = "";
        String req = "select roles from user where username =?";
        PreparedStatement preparedStatement;
        try {
          preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                s1 = resultSet.getString("roles");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return s1;
    }
       
       public User chercherUtilisateurByUsername(String s) {
        User user = null;
     
        PreparedStatement preparedStatement;
        try {
         preparedStatement = cnx.prepareStatement("select * from user where username =?");
            preparedStatement.setString(1, s);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                  user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("username_canonical"),
                        resultSet.getString("email"),
                        resultSet.getString("email_canonical"),
                        resultSet.getString("password"),
                        resultSet.getInt("enabled"),
                        resultSet.getString("roles"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("phone"));
              
                    
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (user == null) {
            return null;
        }
        return user;
    }

        public User chercherUtilisateurByid(Integer s) { 
            User user = null;
     
            PreparedStatement preparedStatement;
            try {
             preparedStatement = cnx.prepareStatement("select * from user where id =?");
                preparedStatement.setInt(1, s);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                      user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("username_canonical"),
                            resultSet.getString("email"),
                            resultSet.getString("email_canonical"),
                            resultSet.getString("password"),
                            resultSet.getInt("enabled"),
                            resultSet.getString("roles"),
                            resultSet.getString("nom"),
                            resultSet.getString("prenom"),
                            resultSet.getString("phone"));


                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            if (user == null) {
                return null;
            }   
            return user;
        }

       
       
       public ArrayList<User> afficherEleve() {
        String query = "SELECT * FROM user WHERE roles LIKE '%a:1:{i:0;s:5:\"ELEVE\";}%'";
        ArrayList<User> myList = new ArrayList<>();
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs= pst.executeQuery();
            while(rs.next())
            {
                User e = new User();
                e.setId(rs.getInt("id"));
                e.setUsername(rs.getString("username"));
                e.setEmail_canonical(rs.getString("username_canonical"));
                e.setEmail(rs.getString("email"));
                e.setEmail_canonical(rs.getString("email_canonical"));
                e.setPassword(rs.getString("password"));
                e.setEnabled(rs.getInt("enabled"));
                e.setRoles(rs.getString("roles"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                
                myList.add(e);
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return myList;
    }
       
       public long recuperer_eleve(User e) {
        ArrayList<User> cl= new ArrayList<User>();
        cl=this.afficherEleve();
        long id=-1;
        //System.out.println("size: " + cl.size());
        for (int i = 0; i <cl.size(); i++) {
            if((cl.get(i).getEmail().equals(e.getEmail()))&&(cl.get(i).getNom().equals(e.getNom())))
            {
                //System.out.println("exist");
                id=cl.get(i).getId();
                break;
            }
            
        }
        return id;
        
    }  
}

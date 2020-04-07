/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Eleve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Smirani
 */
public class EleveService {
    Connection cn = DataSource.getInstance().getCnx();
    
    
    public ArrayList<Eleve> afficherEleve() {
        String query = "Select * from user";
        ArrayList<Eleve> myList = new ArrayList<>();
        try {
            PreparedStatement pst = cn.prepareStatement(query);
            ResultSet rs= pst.executeQuery();
            while(rs.next())
            {
                Eleve e = new Eleve();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setPrenom(rs.getString("prenom"));
                e.setEmail(rs.getString("email"));
                e.setUsername(rs.getString("username"));
                e.setPassword(rs.getString("password"));
               
                myList.add(e);
            }
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        return myList;
    }
  public long recuperer_eleve(Eleve e) {
        ArrayList<Eleve> cl= new ArrayList<Eleve>();
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

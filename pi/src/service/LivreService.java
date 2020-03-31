/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Livre;
import entity.Type;
import gui.LivreController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Smirani
 */
public class LivreService {
    Connection cn = DataSource.getInstance().getCnx();
    private final static String UPLOAD_PATH = "C:/Images";

    private String getExtension(File f) {
        return f.getName().substring(f.getName().lastIndexOf(".") + 1);
    }
    
    private String uploadFile(File f) {
        String fileExtension = getExtension(f);
        Date now = Calendar.getInstance().getTime();
        String uploadedFilename = String.valueOf(now.getTime()) + "." + fileExtension;

        File uploaded = new File(UPLOAD_PATH + uploadedFilename);
        try {
            FileInputStream fis = new FileInputStream(f);
            FileOutputStream fos = new FileOutputStream(uploaded);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            fis.close();
            fos.close();
            return uploadedFilename;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
        
    public boolean AjouterLivre(Livre l,File f){
        String fileName = uploadFile(f);
        if (fileName == "") {
            System.out.println("Error uploading file");
            return true;
        }

        l.setImage(fileName);
        
        String query = "insert into livres (id_type,nom,description,auteur,quantite,image) values('" + l.getId_type().getIdL()+ "','" +l.getNom()+ "','" +l.getDescription()+ "','" +l.getAuteur()+ "','" +l.getQuantite()+ "','" +l.getImage()+ "')";
        System.out.println(query);
        boolean x = false;
        try {
            
            Statement st = cn.createStatement();System.out.println("l ajout mchéé");
            st.executeUpdate(query);System.out.println("l ajout mchéé");
            x=true;System.out.println("l ajout mchéé");
        } catch (SQLException e) {
            
            }
return x;
    }
    
   /* 
    public ArrayList<Livre> AfficherLivre(){
         ArrayList<Livre> livres= new ArrayList<>();
        String requete = "(select idLivre,id_type,nom,description,auteur,quantite,image from livres)";
        try {
            
            Statement st = cn.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                livres.add(recupereResultat(x));
            }
        } catch (SQLException ex) {
            System.out.println("ok");
        }
        return livres;
    }
    */
    public ArrayList<Livre> AfficherLivre() {
        ArrayList<Livre> cours = new ArrayList<>();
        String requete = "(select * from livres)";
        try {
            Statement st = cn.createStatement();
            ResultSet x = st.executeQuery(requete);
            while (x.next()) {
                cours.add(recupereResultat(x));
            }
        } catch (SQLException ex) {
            System.out.println("ok");
        }
        return cours;
    }
    public Livre recupereResultat(ResultSet x) {
        Livre l = new Livre();
        try {
           
                l.setId(x.getInt("idLivre"));
                l.setNom(x.getString("nom"));
                l.setDescription(x.getString("description"));
                l.setAuteur(x.getString("auteur"));
                l.setId_type(retournerType(x.getInt("id_type")));
                l.setQuantite(x.getInt("quantite"));
                l.setImage(x.getString("image"));
               
            
        } catch (SQLException ex) {

        }

        return l;
    }
    public Type retournerType(int id) {
        try {
            PreparedStatement pt = cn.prepareStatement("select * from type where idL=?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                return recupereResultatM(rs);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    
    public Type recupereResultatM(ResultSet x) {
        Type q = new Type();
        try {
            q.setIdL(x.getInt("idL"));
            q.setLibelle(x.getString("Libelle"));

        } catch (SQLException ex) {

        }

        return q;
    }
    
    
            
    /*
    public void update(int id,String nom,String description,String auteur , String image) {
        
        try {
          System.out.println("testttt");
          String req = "UPDATE livres SET nom=?,description=?,auteur=?,image=? WHERE idLivre=?";
            System.out.println(req);
             PreparedStatement pt = cn.prepareStatement(req);
             System.out.println("modiffff");
             pt.setString(1,nom);
             pt.setString(2,description);
               pt.setString(3, auteur);
             //pt.setInt(4,type);
             //pt.setInt(5,quantite);
             pt.setString(4,image);
               pt.setInt(5,id);
                 pt.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(LivreService.class.getName()).log(Level.SEVERE, null, ex);
         }

    }
    */
    public void update(Livre l) {

        String requete = "UPDATE `livres` SET `nom` = '" + l.getNom() + "', `description` = '" + l.getDescription() + "', `auteur` = '" + l.getAuteur() + "', `id_type` = '" + l.getId_type().getIdL() + "', `quantite` = '" + l.getQuantite()+ "', `image` = '" + l.getImage() + "' WHERE `livres`.`idLivre` = '" + l.getId() + "'";
        try {
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException e) {
        }
    }
    public void delete(int id) {
      try {
          PreparedStatement pt = cn.prepareStatement("delete from livres where idLivre =?");
          pt.setInt(1,id);
          pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(LivreService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
   
        public static boolean TxtFiNotEmpty(TextField tf){
        boolean b = false;
        if (tf.getText().length()!=0 || !tf.getText().isEmpty()) {
            b=true;            
        }
    return b;
}
    public static boolean TxtFiNotEmpty(TextField tf, Label lb, String  ErrorMsg){
        boolean b = true;
        String msg=null;
        tf.getStyleClass().remove("error");
        if (!TxtFiNotEmpty(tf)) {
            b=false;
            msg= ErrorMsg;
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
    return b;        
    }
    
    public static boolean TxtFiNumbre(TextField tf){
        boolean b = false;
        if (tf.getText().matches("([0-9]+(\\.[0-9]+)?)+")) {
            b=true;            
        }
    return b;
}
    public static boolean TxtFiNumber(TextField tf, Label lb, String  ErrorMsg){
        boolean b = true;
        String msg=null;
         tf.getStyleClass().remove("error");
        if (!TxtFiNumbre(tf) || !TxtFiNotEmpty(tf)) {
            b=false;
            msg= ErrorMsg;
            lb.setText(msg);
            tf.getStyleClass().add("error");
        }
        lb.setText(msg);
    return b;        
    }


}

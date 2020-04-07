/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DB.DataSource;
import entity.Eleve;
import entity.Livre;
import entity.ReservationLivre;
import entity.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Smirani
 */
public class ReservationService {
    
    Connection cn = DataSource.getInstance().getCnx();
    
    public void reserver_livre(Livre l,  Eleve e) {
        try {
            //ajouter les reservations
            String query = "INSERT INTO `reservationlivre`(`idLivre`,`idEleve`) VALUES (?,?)";

            LivreService se = new LivreService();
            EleveService su = new EleveService();
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setLong(1, se.recuperer_livre(l));
            pst.setLong(2, su.recuperer_eleve(e));
            //reccuperer le nombre des participants  
            String query2 = "select `quantite`, `nbPersonnes` from `livres` WHERE idLivre=?  ";
            PreparedStatement st = cn.prepareStatement(query2);
            st.setLong(1, se.recuperer_livre(l));
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                l.setQuantite(rs.getInt("quantite"));
                l.setNbPersonnes(rs.getInt("nbPersonnes"));
                //incrémenter le nombre des participants
                String querry1 = "UPDATE `livres` SET `quantite` =?,`nbPersonnes` =? WHERE idLivre=?";
                PreparedStatement pst1 = cn.prepareStatement(querry1);
                pst1.setInt(1, l.getQuantite()- 1);
                pst1.setInt(2, l.getNbPersonnes()+1);
                pst1.setLong(3, se.recuperer_livre(l));
                pst1.executeUpdate();
            }

            pst.executeUpdate();
            System.out.println("Votre empreinte du Livre "+l.getNom()+ " a été effectuée avec succés ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la reservation \n " + ex.getMessage());
        }

    }

    
    public void rendre_livre(Livre e, Eleve u) {

        try {
            ReservationLivre r = new ReservationLivre();
            LivreService se = new LivreService();
            EleveService su = new EleveService();

            //reccuperer le nbre de places dispo
            
            String query3 = "select `quantite` ,`nbPersonnes` from `livres` WHERE idLivre=? ";
            PreparedStatement st3 = cn.prepareStatement(query3);
            st3.setLong(1, se.recuperer_livre(e));
            ResultSet rs3 = st3.executeQuery();
            while (rs3.next()) {
                e.setQuantite(rs3.getInt("quantite"));
                //System.out.println(e.getQuantite());

                //diminuer nbre de place dispo 
                String querry1 = "UPDATE `livres` SET `quantite`=? ,`nbPersonnes` =? WHERE idLivre=?";
                PreparedStatement pst1 = cn.prepareStatement(querry1);
                pst1.setInt(1, e.getQuantite()+ 1);
                pst1.setInt(2, e.getNbPersonnes()-1);
                pst1.setLong(3, se.recuperer_livre(e));
                pst1.executeUpdate();
            }            

            // System.out.println(v.getNb_place_dispo());
            String query = "DELETE FROM `reservationlivre` WHERE idLivre=? and idEleve=? ";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setLong(1, se.recuperer_livre(e));
            pst.setLong(2, su.recuperer_eleve(u));
            pst.executeUpdate();
            System.out.println("Votre Réservation a été annulée ! \n");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'annulation \n " + ex.getMessage());

        }

    }
    
    
    public long recuperer_reservation(Livre e, Eleve u) {
        long id = -1;
        ArrayList<ReservationLivre> reservations = new ArrayList<ReservationLivre>();
        reservations = this.afficher_Reservation();
        for (int i = 0; i < reservations.size(); i++) {
            if ((reservations.get(i).getId_livre()== e.getId()) && (reservations.get(i).getId_eleve()== u.getId())) {
                id = reservations.get(i).getId();
                System.out.println(id);
                break;
            }
        }
        return id;
    }
    
    
    public ArrayList<ReservationLivre> afficher_Reservation() {

        ArrayList<ReservationLivre> list = new ArrayList<ReservationLivre>();
        String querry = "SELECT * FROM reservationlivre ";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(querry);
            ReservationLivre r = new ReservationLivre();
            while (rs.next()) {
                ReservationLivre e = new ReservationLivre(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );
                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors d'extraction des données \n" + ex.getMessage());
        }

        return list;
    }
    
    public ArrayList<Livre> DisplayAllReserver() {
        ArrayList<ReservationLivre> list =  new ArrayList<>();
        list = this.afficher_Reservation();
        ArrayList<Livre> livres = new ArrayList<>();
        
        for(int i=0; i< list.size(); i++){
            
            String requete = "select * from livres where idLivre="+list.get(i).getId_eleve();
            try {
                Statement st = cn.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                    livres.add(recupereResultat(x));
                }
            } catch (SQLException ex) {
                System.out.println("Errrrrrrrrrrrrrr");
            }
        }
        return livres;
    }
    //Taffichi par client 
    
    public ArrayList<Livre> DisplayLivreReserver(Eleve e) {
        ArrayList<ReservationLivre> list =  new ArrayList<>();
        list = this.afficher_Reservation();
        ArrayList<Livre> livres = new ArrayList<>();
        
        for(int i=0; i< list.size(); i++){
            if(e.getId()==list.get(i).getId_livre()){
            String requete = "select * from livres where idLivre="+list.get(i).getId_eleve();
            try {
                Statement st = cn.createStatement();
                ResultSet x = st.executeQuery(requete);
                while (x.next()) {
                    livres.add(recupereResultat(x));
                }
            } catch (SQLException ex) {
                System.out.println("Errrrrrrrrrrrrrr");
            }
        }
        }
        return livres;
    }

        public Livre recupereResultat(ResultSet x) {
        Livre l = new Livre();
        try {          
                l.setId(x.getInt("idLivre"));
                System.out.println(l.getNom());
                l.setNom(x.getString("nom"));
                l.setDescription(x.getString("description"));
                l.setAuteur(x.getString("auteur"));
                l.setId_type(retournerType(x.getInt("id_type")));
                l.setQuantite(x.getInt("quantite"));
                l.setImage("file:C:\\3A13\\ressources\\"+x.getString("image"));                        
        } catch (SQLException ex) {

        }

        return l;
    }
    
    
    public Type retournerType(int id) {
        try {
            PreparedStatement pt = cn.prepareStatement("select * from type where idL=?");
            pt.setInt(1, id);
            ResultSet ty = pt.executeQuery();
            while (ty.next()) {
                return recupereType(ty);
            }
        } catch (SQLException ex) {

        }
        return null;
    }
    
    
    public Type recupereType(ResultSet x) {
        Type q = new Type();
        try {
            q.setIdL(x.getInt("idL"));
            q.setLibelle(x.getString("Libelle"));

        } catch (SQLException ex) {

        }

        return q;
    }
    
    
    
    

}
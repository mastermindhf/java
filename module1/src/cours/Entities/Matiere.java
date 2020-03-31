/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Entities;

/**
 *
 * @author ASUS
 */
public class Matiere {
    private int id;
    private String Libelle;

    public Matiere() {
    }

    public Matiere(int id, String Libelle) {
        this.id = id;
        this.Libelle = Libelle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String Libelle) {
        this.Libelle = Libelle;
    }

    @Override
    public String toString() {
        return Libelle ;
    }
    
}

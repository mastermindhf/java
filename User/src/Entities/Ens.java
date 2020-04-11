/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author farou
 */
public class Ens {
    private int id;
    private String Nom ,Prenom,Email ;

    public Ens(int id, String Nom, String Prenom, String Email) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "Ens{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Email=" + Email + '}';
    }

    
    public Ens(String Nom, String Prenom, String Email) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
     
    
}

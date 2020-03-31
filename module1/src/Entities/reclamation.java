/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author farou
 */
public class reclamation {
    private int id;
    private String Nom;
    private String Prenom;
    private String Tel;
    private String Email;
    private String Type;
    private String Screenshot;
    private String Objet;
    private String Reclamation;
    private Date Date;
    private String Etat;
    public reclamation() {
    }

    public reclamation(String Nom) {
        this.Nom = Nom;
    }

    public reclamation(int id, String Nom, String Prenom, String Tel, String Email, String Type, String Screenshot, String Objet, String Reclamation, Date Date, String Etat) {
        this.id = id;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Type = Type;
        this.Screenshot = Screenshot;
        this.Objet = Objet;
        this.Reclamation = Reclamation;
        this.Date = Date;
        this.Etat = Etat;
    }

    public reclamation(String Nom, String Prenom, String Tel, String Email, String Type, String Screenshot, String Objet, String Reclamation, Date Date, String Etat) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Tel = Tel;
        this.Email = Email;
        this.Type = Type;
        this.Screenshot = Screenshot;
        this.Objet = Objet;
        this.Reclamation = Reclamation;
        this.Date = Date;
        this.Etat = Etat;
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

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getScreenshot() {
        return Screenshot;
    }

    public void setScreenshot(String Screenshot) {
        this.Screenshot = Screenshot;
    }

    public String getObjet() {
        return Objet;
    }

    public void setObjet(String Objet) {
        this.Objet = Objet;
    }

    public String getReclamation() {
        return Reclamation;
    }

    public void setReclamation(String Reclamation) {
        this.Reclamation = Reclamation;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Tel=" + Tel + ", Email=" + Email + ", Type=" + Type + ", Screenshot=" + Screenshot + ", Objet=" + Objet + ", Reclamation=" + Reclamation + ", Date=" + Date + ", Etat=" + Etat + '}';
    }

   




   
    
    
}

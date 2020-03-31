/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cours.Entities;

import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Cours {
    private int id;
    private Matiere m;
    private String libelle,niveau,contract;

    public Cours() {
    }

    public Cours(Matiere m, String libelle, String niveau, String contract) {
        this.m = m;
        this.libelle = libelle;
        this.niveau = niveau;
        this.contract = contract;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

 



    public Matiere getM() {
        return m;
    }

    public void setM(Matiere m) {
        this.m = m;
    }

    public Cours(String libelle, String niveau) {
        this.libelle = libelle;
        this.niveau = niveau;
    }

    public void setId(int id) {
        this.id = id;
    }


    
    
    
    public int getId() {
        return id;
    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return  libelle ;
    }

 

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cours other = (Cours) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        return true;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Smirani
 */
public class Eleve extends User {
    private String username;
    
    public Eleve(){
        
    }

    public Eleve(String username) {
        this.username = username;
    }

    public Eleve(String username, long id, String password, String nom, String prenom, String email) {
        super(id, password, nom, prenom, email);
        this.username = username;
    }

    public Eleve(String username, String password, String nom, String prenom, String email) {
        super(password, nom, prenom, email);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Eleve{" + super.toString()+ "username=" + username + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Eleve other = (Eleve) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}

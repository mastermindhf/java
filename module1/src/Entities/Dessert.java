/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author admin
 */
public class Dessert {
    int id; 
    String libelle; 
    int calories; 

    public Dessert(int id, String libelle, int calories) {
        this.id = id;
        this.libelle = libelle;
        this.calories = calories;
    }
    
    public Dessert(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return ""+ libelle +"";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author admin
 */
public class Plat {
    
    private Integer id;
    private Integer calories;
    private Integer likes;
    private Integer dislikes;
    private Integer res;
    private String libelle; 
    private String description;
   

    public Plat() {
        res=0;
        likes=0;
        dislikes=0;
    }
 

    @Override
    public String toString() {
        return "" + libelle + "";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.libelle);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + this.calories;
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
        final Plat other = (Plat) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

  

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

       public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
       
    
    public void setId(int id) {
        this.id=id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getRes() {
        return res;
    }

    public void setRes(Integer res) {
        this.res = res;
    }
    
    
    

    public Plat(int calories,int likes,int dislikes,int res, String libelle, String description) {
       
        this.calories = calories;
        this.res=res;
        this.likes=likes;
        this.dislikes=dislikes;
        this.libelle = libelle;
        this.description = description;
    }
    

  

   
    
}

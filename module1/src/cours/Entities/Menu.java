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
public class Menu {
    
    int id; 
    int semaine;
    int plat;
    int dessert;
    int jour;
    int calories;
    
   
    
   public Menu(int id , int plat)
    {
        this.id=id;
        this.plat=plat;
        calories=0;
    }
      public Menu(int semaine , int plat,int dessert,int jour)
    {
        
        this.plat=plat;
        this.semaine=semaine;
        this.dessert=dessert;
        this.jour=jour;
        calories=0;
              
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Menu other = (Menu) obj;
        if (this.semaine != other.semaine) {
            return false;
        }
        if (this.jour != other.jour) {
            return false;
        }
        return true;
    }
   
   
   public Menu(){
       this.calories=0;
   }

    
    public int getId() {
        return id;
    }

    public int getSemaine() {
        return semaine;
    }

    public void setSemaine(int semaine) {
        this.semaine = semaine;
    }

    public int getDessert() {
        return dessert;
    }

    public void setDessert(int dessert) {
        this.dessert = dessert;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlat() {
        return plat;
    }

    public void setPlat(int plat) {
        this.plat = plat;
    }
    
}

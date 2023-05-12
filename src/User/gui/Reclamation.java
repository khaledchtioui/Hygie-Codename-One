/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.gui;

/**
 *
 * @author Khaled
 */
public class Reclamation {
    
    private int id ; 
    private String titre ; 
    private String description ;
    private String type ; 
    private int Status ;
    private String date ;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    

    public Reclamation() {
    }

    public Reclamation(int id, String titre, String description, String type, int Status) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.Status = Status;
    }

    public Reclamation(String titre, String description, String type, int Status) {
        this.titre = titre;
        this.description = description;
        this.type = type;
        this.Status = Status;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getStatus() {
        return Status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "titre=" + titre + '}';
    }
    
    
    
    
    
}

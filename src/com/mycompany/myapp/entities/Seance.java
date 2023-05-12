package com.mycompany.myapp.entities;

public class Seance {
    private int id;
    private String titre;
    private String description;
    private String prix;
    private String image;


    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Seance(String titre, String image, String description, String prix) {
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.prix = prix;

    }

    public Seance() {
    }

    public Seance(int id, String titre, String description, String prix, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                "titre='" + titre + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", prix='" + prix + '\'' +
                '}';
    }


}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Yassine
 */
public class Article {
 private int id;
    private String titre;
    private String description;
    private Date dateCreation;
    private String categorie;
    private String image ;       

    public Article() {
    }

    public Article(int id, String titre, String description, Date dateCreation, String categorie, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.dateCreation = dateCreation;
        this.categorie = categorie;
        this.image = image;
    }

    public Article(String titre, String description, Date dateCreation, String categorie, String image) {
        this.titre = titre;
        this.description = description;
        this.dateCreation = dateCreation;
        this.categorie = categorie;
        this.image = image;
    }

    public Article(String titre, String description, String categorie, String image) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

   
    @Override
    public String toString() {
        return "article{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", dateCreation=" + dateCreation + ", categorie=" + categorie + ", image=" + image +  '}';
    }
    
    

    
}

package com.mycompany.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.ServiceArticle;
import java.util.ArrayList;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Yassine
 */
public class listarticle extends Form {
     public listarticle(Form previous) {
        ArrayList<Article> articles;
        setTitle("List article");
        setLayout(BoxLayout.y());
        articles = ServiceArticle.getInstance().getArticles();
        for (Article article : articles) {
            add(new Label("Titre: " + article.getTitre()));
         //   add(new Label("Description: " + article.getDescription()));
            add(new Label("categorie: " + article.getCategorie()));
            add(new Label("Image: " +article.getImage()));
            addElement(article);

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});
    
}
      public void addElement(Article articles){

         


        }
}

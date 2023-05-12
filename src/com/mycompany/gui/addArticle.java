package com.mycompany.gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Article;
import com.mycompany.myapp.entities.ServiceArticle;

/**
 *
 * @author Yassine
 */
public class addArticle extends Form{

    ActionListener show;
    public addArticle(Form previous){
    setTitle("Add Seance");
        setLayout(BoxLayout.y());
        TextField tftitre = new TextField("","Titre");
        TextField tfdescription = new TextField("","Description");
        TextField tfcategorie = new TextField("","categorie");
        TextField tfimage = new TextField("","Image");
        Button btnValider = new Button("Add Article");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((tftitre.getText().length()==0)||(tfdescription.getText().length()==0)||(tfcategorie.getText().length()==0)||(tfimage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Article articles = new Article(tfdescription.getText(),tftitre.getText(),tfcategorie.getText(),tfimage.getText());
                        if( ServiceArticle.getInstance().addArticle(articles.getDescription(),articles.getTitre(),articles.getCategorie(),articles.getImage()))
                            Dialog.show("Success","Ajout effecuter",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});
        addAll(tfdescription,tftitre,tfcategorie,tfimage,btnValider);
    
    
    }
    
}

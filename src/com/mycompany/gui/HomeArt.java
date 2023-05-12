/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

/**
 *
 * @author sayed
 */
public class HomeArt extends BaseForm{

    public HomeArt(Resources res) {
        
         Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        setLayout(BoxLayout.y());
                super.addSideMenu(res);

                add(new Label(" "));
                                add(new Label(" "));

                                                add(new Label(" "));



    Button btnAddArticle = new Button("Ajouter article");
    Button btnListarticles= new Button("Articles");
          btnListarticles.addActionListener(e-> new listarticle(this).show());


        btnAddArticle.addActionListener(e-> new addArticle(this).show());
                add(btnAddArticle);
                add(btnListarticles);


            }
        
        
    }
    
    
    
    


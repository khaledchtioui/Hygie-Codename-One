/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Questions;
import com.mycompany.myapp.entities.Reponse;
import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author Khaled
 */
public class AddReponsebyquestion extends Form{

    public AddReponsebyquestion(Form previous ,int id) {
        
        
          setTitle("Add a new Reponse");
          setLayout(BoxLayout.y());
        
        TextField tfreponse = new TextField("","Reponse");
        ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("True")  ; 
        combotype.addItem("False"); 
                Button btnValider = new Button("Add Reponse");
                
                
               btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((tfreponse.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        boolean status=false;
                        if(combotype.getSelectedItem().equals("True"))
                            status=true  ;
                        Reponse t = new Reponse(tfreponse.getText().toString(),status,id);
                        if( ServiceTask.getInstance().addReponse(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });

        addAll(tfreponse,combotype,btnValider);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    
    
    
}

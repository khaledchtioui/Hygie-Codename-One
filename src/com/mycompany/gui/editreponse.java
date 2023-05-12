/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import User.gui.Questionnaire;
import User.gui.Reponse;
import com.mycompany.myapp.entities.ServiceTask;

/**
 *
 * @author Khaled
 */
public class editreponse  extends Form{
       public Reponse reponsee ;

    public editreponse(Form previous ,int id) {
        
        
          setTitle("Edit Question");
        setLayout(BoxLayout.y());
                 reponsee = ServiceTask.getInstance().getreponse(id);
                 boolean type = reponsee.isType()  ;
                   TextField tfreponse = new TextField(reponsee.getReponse(),"Reponse");
        ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("True")  ; 
        combotype.addItem("False"); 
         if(type==true)
        {
            combotype.setSelectedItem("True");
        }
         else
         {
                         combotype.setSelectedItem("False");

         }
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
                        Reponse t = new Reponse(id,tfreponse.getText().toString(),status,reponsee.getId_question());
                        if( ServiceTask.getInstance().uppReponse(t))
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


         addAll(btnValider,tfreponse,combotype)   ; 
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    
}

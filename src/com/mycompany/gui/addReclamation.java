/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import User.gui.Reclamation;
import User.gui.Task;
import com.mycompany.myapp.entities.ServiceRec;

/**
 *
 * @author Khaled
 */
public class addReclamation extends Form{

    public addReclamation(Form previous) {
        
         setTitle("Add a new Reclamation");
        setLayout(BoxLayout.y());
        
        TextField tfTitre = new TextField("","Titre");
        TextField tfType = new TextField("","Type");
        TextField tfdesc = new TextField("","Description");
        ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("Non Traité"); 
        combotype.setSelectedItem("Non Traité");

        Button btnValider = new Button("Send Reclamation");

                btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitre.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                       
                        Reclamation  t = new Reclamation(tfTitre.getText().toString(),tfdesc.getText().toString(),tfType.getText(),0);
                        if( ServiceRec.getInstance().addReclamation(t))
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

        
        

        addAll(tfTitre,tfType,tfdesc,combotype,btnValider)  ; 
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        
    }
    
    
    
    
}

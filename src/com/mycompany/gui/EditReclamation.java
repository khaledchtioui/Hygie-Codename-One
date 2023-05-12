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
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import User.gui.Reclamation;
import com.mycompany.myapp.entities.ServiceRec;

/**
 *
 * @author Khaled
 */
public class EditReclamation extends Form {
    
    
           public Reclamation rec ;


    public EditReclamation(Form previous , int id) {
        
            setTitle("Edit Reclamation");
        setLayout(BoxLayout.y());

                 rec = ServiceRec.getInstance().getreclamation(id);

int status =rec.getStatus();

        TextField tftitre = new TextField(rec.getTitre(),"Titre");
        TextField tfType = new TextField(rec.getType(),"Type");
        TextField tfdesc = new TextField(rec.getDescription(),"Description");
         ComboBox<String> combotype = new ComboBox<>() ; 
        combotype.addItem("Traité"); 
        combotype.addItem("En Cours"); 
        combotype.addItem("Non Traité"); 
                       System.out.println("aloooo 2");

if(status == 2)
{
        combotype.setSelectedItem("Traité");
}
else if(status == 1)
{
        combotype.setSelectedItem("En Cours");
}
else if(status == 0)
{
        combotype.setSelectedItem("Non Traité");

}
             System.out.println("aloooo 3");
              Button btnValider = new Button("Add Reponse");
                btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tftitre.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        int statu = 0 ;
                        if(combotype.getSelectedItem().equals("En Cours"))
                        {
                            statu =    1  ;
                        }
                        else if(combotype.getSelectedItem().equals("Non Traité"))
                        {
                            statu = 2 ; 
                        }
                        else if(combotype.getSelectedItem().equals("Traité"))
                        {
                            statu = 0  ; 
                        }
                        Reclamation t = new Reclamation(id,tftitre.getText().toString(),tfdesc.getText().toString(),tfType.getText().toString(),statu);
                        if( ServiceRec.getInstance().uppReclamation(t))
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


        addAll(tftitre,tfdesc,tfType,combotype,btnValider)   ;
         

        
        
        
    }
    
    
    
    
}

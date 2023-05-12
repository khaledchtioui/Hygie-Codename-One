/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import User.gui.Reclamation;
import User.gui.Task;
import com.mycompany.myapp.entities.ServiceRec;
import java.util.ArrayList;

/**
 *
 * @author Khaled
 */
public class ListReclamationForm extends Form {

    public ListReclamationForm(Form previous) {
        
          setTitle("List Reclamation");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Reclamation> reclamations = ServiceRec.getInstance().getAllReclamation();
        for (Reclamation t : reclamations) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        
        
        
    }
    
    public void addElement(Reclamation reclamations) {

String etat ="Non Traité"  ; 
        int status = reclamations.getStatus()  ; 
        if(status==2)
            etat ="Traité"  ; 
        else if (status==1)
            etat="En Cours" ; 
            
        
                Button btnShowrec = new Button("Show Reclamation");
                Button btnupprec = new Button("update Reclamation");
                Button btndel = new Button("Delete Reclamation");

                Label lbltitre = new Label(reclamations.getTitre());
                
                   btnShowrec.addActionListener(e-> new ReclamationDetailForm(this,reclamations).show());
                   btnupprec.addActionListener(e-> new EditReclamation(this,reclamations.getId()).show());
                  btndel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
    
                  
                         if( ServiceRec.getInstance().suppReclamation(reclamations))
                            {  
                                Dialog.show("Success","Connection accepted",new Command("OK"));
                            }else
                         {
                           Dialog.show("Error","Server error",new Command("OK"));
    
                         }
                        }
            });

                
               

        

        addAll(lbltitre,btnShowrec,btnupprec,btndel);

    }
    
    
    
    
}

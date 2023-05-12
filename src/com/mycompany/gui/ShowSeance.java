package com.mycompany.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.entities.services.ServiceSeance;

import java.util.ArrayList;

public class ShowSeance extends Form {
    public ShowSeance(Form previous) {
        ArrayList<Seance> seances;
        setTitle("List Seances");
        setLayout(BoxLayout.y());
        seances = ServiceSeance.getInstance().getSeances();
        for (Seance seance : seances) {
            add(new Label("Titre: " + seance.getTitre()));
            add(new Label("Description: " + seance.getDescription()));
            add(new Label("Prix: " + seance.getPrix()));
            add(new Label("Image: " + seance.getImage()));
            addElement(seance);

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});

    }
        public void addElement(Seance seances){

            Button btn_up =new Button("Update"+seances.getId()+seances.getTitre()+seances.getDescription()+seances.getPrix()+seances.getImage())  ;
            btn_up.addActionListener(e-> new UpdateSeance(this,seances.getId()).show());
            Button btn_2 = new Button("Delete"+seances.getId())  ;
            btn_2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {


                    if( ServiceSeance.getInstance().deleteSeance(seances))
                    {
                        Dialog.show("Success","Connection accepted",new Command("OK"));
                    }else
                    {
                        Dialog.show("Error","Server error",new Command("OK"));

                    }
                }
            });

            btn_up.addActionListener(e-> new UpdateSeance(this,seances.getId()).show());



            addAll(btn_2,btn_up);


        }
}

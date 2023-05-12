package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.entities.services.ServiceSeance;
import java.util.ArrayList;

public class UpdateSeance extends Form {
 Seance seances=new Seance();
    public UpdateSeance(Form previous,int id){
        setTitle("Update Seance");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});
         seances = ServiceSeance.getInstance().getSeances().get(id);


        TextField tftitre = new TextField(seances.getTitre(),"Titre");
        TextField tfdescription = new TextField(seances.getDescription(),"Description");
        TextField tfprix = new TextField(seances.getPrix().toString(),"Prix");
        TextField tfimage = new TextField(seances.getImage(),"Image");

        addAll(tftitre,tfdescription,tfprix,tfimage);
        Button btnValider = new Button("Update Seance");
           add(btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(com.codename1.ui.events.ActionEvent actionEvent) {
                if ((tftitre.getText().length()==0)||(tfdescription.getText().length()==0)||(tfprix.getText().length()==0)||(tfimage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Seance seances = new Seance(tftitre.getText(),tfimage.getText(),tfdescription.getText(),tfprix.getText());
                        if( ServiceSeance.getInstance().updateSeance(seances.getId(),seances.getTitre(),seances.getImage(),seances.getDescription(),seances.getPrix()))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }

        });

    }
}

package com.mycompany.gui;

import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.entities.services.ServiceSeance;

public class AddSeance extends Form {
    public AddSeance(Form previous) {
        setTitle("Add Seance");
        setLayout(BoxLayout.y());
        TextField tftitre = new TextField("","Titre");
        TextField tfdescription = new TextField("","Description");
        TextField tfprix = new TextField("","Prix");
        TextField tfimage = new TextField("","Image");
        Button btnValider = new Button("Add Seance");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if ((tftitre.getText().length()==0)||(tfdescription.getText().length()==0)||(tfprix.getText().length()==0)||(tfimage.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Seance seances = new Seance(tftitre.getText(),tfimage.getText(),tfdescription.getText(),tfprix.getText());
                        if( ServiceSeance.getInstance().addSeance(seances.getTitre(),seances.getImage(),seances.getDescription(),seances.getPrix()))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});
        addAll(tftitre,tfdescription,tfprix,tfimage,btnValider);
    }
}

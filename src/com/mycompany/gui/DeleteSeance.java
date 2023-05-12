package com.mycompany.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class DeleteSeance extends Form {
    public DeleteSeance(Form previous) {
        setTitle("Delete Seance");
        setLayout(BoxLayout.y());
        add(new Label("Delete Seance"));

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)-> {previous.showBack();});
    }

}

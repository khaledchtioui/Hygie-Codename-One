/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package User.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.datatransfer.DropTarget;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.L10NManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompany.myapp.entities.services.UserServices;
import com.mycompany.myapp.utils.UserSession;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.URLImage;
import com.mycompany.myapp.utils.Statics;
import com.mycompany.myapp.utils.UserSession;
import java.io.IOException;
import java.util.Random;
import com.mycompany.gui.BaseForm;
import com.mycompany.myapp.entities.User;



/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
 UserServices us;
    String Imagecode;
   String filePath="";
    public ProfileForm(Resources res, User user) {
      
        super("Newsfeed", BoxLayout.y());
         this.us = UserServices.getInstance();
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
Button imge = new Button();  
        
        
         String linkProfilePic;
                //Profile picture mask init
         if(user.getImage()==null){
             
             linkProfilePic = "file://C://Users//sayed//PIDEV//PIDEV_INTEGRATION//PIDEV_SPORTIFY//public/user.png";
               imge.setText("Télécharger in image"); 

         }else{
                          linkProfilePic = "file://C://Users//sayed//PIDEV//PIDEV_INTEGRATION//PIDEV_SPORTIFY//public/"+user.getImage();
            System.err.println(user.getImage());
               imge.setText("Modifier image");  
         }

     EncodedImage   enc = (EncodedImage) res.getImage("round-mask.png");
        Image roundMask = Image.createImage(enc.getWidth(), enc.getHeight(), 0xff000000);
        Graphics gr = roundMask.getGraphics();
        gr.setColor(0xffffff);
        gr.fillArc(0, 0, enc.getWidth(), enc.getWidth(), 0, 360);
    Image    profilePic = URLImage.createToStorage(enc, linkProfilePic, linkProfilePic);
        profilePic.scale(enc.getWidth(), enc.getHeight());
     ImageViewer   imgv = new ImageViewer(profilePic);
        Object mask = roundMask.createMask();
        profilePic = profilePic.applyMaskAutoScale(mask);
        imgv.setImage(profilePic);
        //Profile picture mask init ends

                add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(1, 
                           
                            FlowLayout.encloseCenter(
                             imgv
                           
                    )
                )
        )));
        
        if(user.getFullname()== null||user.getEmail()==null||user.getCin()==null||user.getAddress()==null ){
    Dialog.show("Alert", "compléter ton inscription", new Command("OK"));
   
}


        TextField fullname = new TextField(user.getFullname());
        fullname.setUIID("TextFieldBlack");
        addStringValue("Fullname", fullname);
   
        TextField address = new TextField(user.getAddress());
        address.setUIID("TextFieldBlack");
        addStringValue("Address", address);

         
        TextField email = new TextField(user.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
         
        TextField cin = new TextField(user.getCin(), "CIN", 20, TextField.BASELINE);
        cin.setUIID("TextFieldBlack");
        addStringValue("CIN", cin);
        
       
   Button edit = new Button("Modifier");    

Button sup = new Button("Supprimer account");  

 
   add2bo(imge, edit);
   addStringValue("", sup);

       


   edit.requestFocus();
   
        edit.addActionListener((ActionEvent e) ->{
        
      if(edit.getText().equals("Modifier"))  {
         edit.setText("Sauvgarder"); 
         fullname.setEditable(true);
        address.setEditable(true);  
        email.setEditable(true);  
        cin.setEditable(true); 


      }else{
        
      if (Dialog.show("Confirmer", "Do you want to proceed?", "OK", "Cancel")) {
          
                if (!fullname.getText().equals("")) {
                    user.setFullname(fullname.getText());
                }
                if (!address.getText().equals("")) {
                   user.setAddress(address.getText());

                }
 
                L10NManager l10n = L10NManager.getInstance();  

                if (!email.getText().equals("")) {

                    user.setEmail(email.getText());
                }
                if (!cin.getText().equals("")) {
                    user.setCin(cin.getText());
                }
                

if(us.updateUser(UserSession.instance.getU())){
     
     new ProfileForm(res,UserSession.instance.getU()).show();
 }


}else{
          
       new ProfileForm(res,UserSession.instance.getU()).show();
      }
                //User clicks on ok to delete account
         
             
edit.setText("Modifier"); 
         fullname.setEditable(true);
        address.setEditable(true);  
        email.setEditable(true);  
        cin.setEditable(true); 
          
      }
        
        
        
        
        
        });
 
         
         
   imge.addActionListener(new ActionListener() {
     @Override
            public void actionPerformed(ActionEvent evt) {
               
                String fileName =randomName()+".jpg";
                
                MultipartRequest cr = new MultipartRequest();
                String filePath = Capture.capturePhoto();
                

                String mime = "image/jpeg";
                try {
                    cr.addData("file", filePath, mime);
                } catch (IOException ex) {
                }
                cr.setFilename("file", fileName);//any unique name you want

                cr.setUrl("http://127.0.0.1/imageServer.php");
                cr.setPost(true);
               
                InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);

                System.out.println(filePath);

                NetworkManager.getInstance().addToQueueAndWait(cr);
                user.setImage(fileName);
//            if(us.updateimg(UserSession.instance.getU()))   {
//                UserSession.instance.getU().setImage(fileName);
//             
//               new ProfileForm(res,UserSession.instance.getU()).show(); 
//                
//            } 
                 
                
            }
   
   });
   
   
   
    sup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
        
  if (Dialog.show("Confirmer", "Do you want to proceed?", "OK", "Cancel")) {
  
  
  us.delete(UserSession.instance.getU());
  UserSession.instance.cleanUserSession();
  
   new SignInForm(res).show(); 
  
  }
            }
        });
   
   
   
   
   
   
         fullname.setEditable(true);
        address.setEditable(true);  
        email.setEditable(true);  
        cin.setEditable(true); 


    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }


    private void add2bo(Component k, Component v) {
        add(BorderLayout.west(k).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }

    String randomName() {
        Random rnd = new Random();
        String lettersAndNumbersAndSymbols = "abcdefghijklmnopqrstuvwxyz0123456789_";
        String name = "";
        for (int i = 0; i < 51; i++) {
            name += lettersAndNumbersAndSymbols.charAt(rnd.nextInt(lettersAndNumbersAndSymbols.length()));
        }
        return name;
    }





}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Yassine
 */
public class ServiceArticle {
     public ConnectionRequest req;
    private static ServiceArticle instance;
    private ArrayList<Article> Articles;

    private ServiceArticle() {
    req = new ConnectionRequest();
    }
     public static ServiceArticle getInstance() {
         if(instance == null)
             instance = new ServiceArticle();
         return instance;
    } 
    public Boolean addArticle(String titre, String description, String categorie, String image) {
    String url = Statics.BASE_URL+"addjson?description="+description+"&published=True&Titre="+titre+"&categorie="+categorie+"&image="+image;
    req.setPost(false);
    System.out.println(url);

       

    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setPost(false);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            boolean resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return true;
}
      private ArrayList<Article> getArticle() {
    ArrayList<Article> articles = new ArrayList<>();
        String url = Statics.BASE_URL + "json";
        req.setUrl(url); 
          System.out.println(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Article t = new Article();
                        t.setDescription(obj.get("descritpion").toString());
                        t.setTitre(obj.get("titre").toString());
                        t.setImage(obj.get("image").toString());
                        t.setCategorie(obj.get("categorie").toString());
                        articles.add(t);
                    }
                } catch (IOException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
        public ArrayList<Article> getArticles() {
        String url = Statics.BASE_URL + "json";
            System.out.println(url);
            
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            Articles = parseArticles(new String(req.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         return Articles;

    }
private ArrayList<Article> parseArticles(String jsonText)  {
        try {
            Articles = new ArrayList<>();
            JSONParser jsonp = new JSONParser();
            Map<String, Object> SeancesListJson = jsonp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) SeancesListJson.get("root");
            for (Map<String, Object> obj : list) {
                String titre = "null";
            String image = "null";
            String description = "null";
            String categorie= "null";

                if (obj.get("descritpion")!= null) {
                    description = obj.get("description").toString();
                }
                if (obj.get("titre")!= null) {
                    titre = obj.get("titre").toString();
                }
                if (obj.get("categorie")!= null) {
                   categorie = obj.get("description").toString();
                }
                if (obj.get("image")!= null) {
                   image = obj.get("image").toString();
                }
                Article t = new Article( titre, description, categorie,image );
                Articles.add(t);





            }
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Articles;
    }

}

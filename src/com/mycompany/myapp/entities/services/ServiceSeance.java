package com.mycompany.myapp.entities.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Seance;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServiceSeance {
    public ConnectionRequest req;
    private static ServiceSeance instance;
    private ArrayList<Seance> Seances;

    private ServiceSeance() {
    req = new ConnectionRequest();
}

    public static ServiceSeance getInstance() {
         if(instance == null)
             instance = new ServiceSeance();
         return instance;
    }
    public Boolean addSeance(String titre, String image,String description, String prix) {
        String url = Statics.BASE_URL+"addjson0";
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("titre", titre);
        req.addArgument("image", image);
        req.addArgument("description", description);
        req.addArgument("prix", prix+"");

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                boolean resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
   private ArrayList<Seance> getAllseances() {
    ArrayList<Seance> seances = new ArrayList<>();
        String url = Statics.BASE_URL + "alljson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                        Seance t = new Seance();
                        float id = Float.parseFloat(obj.get("id").toString());
                        t.setId((int) id);
                        t.setTitre(obj.get("titre").toString());
                        t.setImage(obj.get("image").toString());
                        t.setDescription(obj.get("description").toString());
                        t.setPrix(obj.get("prix").toString());
                        seances.add(t);
                    }
                } catch (IOException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return seances;
    }
    public ArrayList<Seance> getSeances() {
        String url = Statics.BASE_URL + "show";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            Seances = parseSeances(new String(req.getResponseData()));
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
         return Seances;

    }

    private ArrayList<Seance> parseSeances(String jsonText)  {
        try {
            Seances = new ArrayList<>();
            JSONParser jsonp = new JSONParser();
            Map<String, Object> SeancesListJson = jsonp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) SeancesListJson.get("root");
            for (Map<String, Object> obj : list) {
                String titre = "null";
            String image = "null";
            String description = "null";
            String prix = "null";

                if (obj.get("titre")!= null) {
                    titre = obj.get("titre").toString();
                }
                if (obj.get("image")!= null) {
                    image = obj.get("image").toString();
                }
                if (obj.get("description")!= null) {
                    description = obj.get("description").toString();
                }
                if (obj.get("prix")!= null) {
                    prix = obj.get("prix").toString();
                }
                Seance t = new Seance( titre, image, description, prix);
                Seances.add(t);





            }
        }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Seances;
    }
    public Boolean deleteSeance(Seance seance) {
        String url = Statics.BASE_URL + "delete/" + seance.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
    public Boolean updateSeance(int id,String titre, String image, String description, String prix) {
        String url = Statics.BASE_URL + "update/"+id+"/?titre="+titre+"&image="+image+"&description="+description+"&prix="+prix;
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("titre", titre);
        req.addArgument("image", image);
        req.addArgument("description", description);
        req.addArgument("prix", prix);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
}

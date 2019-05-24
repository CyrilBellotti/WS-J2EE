/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.model;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author cesi
 */
@Named(value = "paymentOrderModel")
@RequestScoped
public class PaymentOrderBean{

    private final String baseURL ="http://localhost:11080/bankFacade-war/banking/payment";
    private List<PaymentOrder> paymentOrders = new ArrayList<>();
    
    @Inject private Client client;
    
   @PostConstruct
    void init(){
     loadAllPayments();    
    }   
   public String cancelPayment(Long id){
        WebTarget target = client.target(baseURL).path("{id}").resolveTemplate("id", id);
        target.request().delete();
        loadAllPayments();
       return null;
   }

    public List<PaymentOrder> getPaymentOrders() {
        return paymentOrders;
    }
    
   private void loadAllPayments(){
       //on vide la liste avant de charger les paiements
       paymentOrders.clear();

        WebTarget target = client.target(baseURL).path("payments");
        Response resp = target.request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        String jsonContent = resp.readEntity(String.class);
        resp.close();
        //lecture de l'entité au format JSON contenu dans la réponse
        try(JsonReader jreader = Json.createReader(new StringReader(jsonContent));){
        //objet Java représentant un tableau json
            JsonArray jArray = jreader.readArray();
            for(int i = 0;i<jArray.size();i++){//pour chaque entrée du tableau
                JsonObject jObject = jArray.getJsonObject(i);//on récupère l'objet json
                //on récupère la valeur de chaque donnée
                Long id =jObject.getJsonNumber("id").longValue();
                Double amount = jObject.getJsonNumber("amount").doubleValue();
                //on construit l'URL localisant un ordre de paiement
                String location = baseURL+"/"+id;
                //on alimente la liste avec un ordre de paiement
                paymentOrders.add(new PaymentOrder(location, amount, id));    
            }
        }              

   }    
    
}

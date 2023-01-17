package com.quinbay.retailer.service;


import com.quinbay.retailer.functions.Retailerinterface;
import com.quinbay.retailer.model.Product;
import com.quinbay.retailer.model.Retailer;
import com.quinbay.retailer.model.Wholesaler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Retailerservice implements Retailerinterface {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public String addRetailer(Retailer r, List<Retailer> rList){
        rList.add(r);
        return "Retailer added";
    }

    @Override
    public Retailer getRetailer(int r_id, List<Retailer> rList){
        Retailer temp = new Retailer();
        for(Retailer r: rList){
            if(r.getId() == r_id){
                return r;
            }
        }
        return temp;
    }

    @Override
    public String deleteRetailer(int r_id, List<Retailer> rList){
        for(Retailer r: rList){
            if(r.getId() == r_id){
                rList.remove(r);
                return "Retailer deleted";
            }
        }
        return "Retailer not found";
    }

    @Override
    public String allocateRetailer(int r_id, int w_id, int p_id, int stock, List<Retailer> rList){

        List<Wholesaler> wList = new ArrayList<>();
        List<Product> pTempList = new ArrayList<>();


        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/wholesaler/getWholesalerStock")
                .queryParam("w_id", w_id)
                .queryParam("p_id", p_id)
                .toUriString();
        Product productRetrieved = restTemplate().getForObject(url, Product.class);

        String urlone = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/wholesaler/getWholesaler")
                .queryParam("w_id", w_id)
                .toUriString();
        Wholesaler wholesalerRetrieved = restTemplate().getForObject(urlone, Wholesaler.class);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String urltwo = UriComponentsBuilder.fromHttpUrl("http://localhost:8081/wholesaler/reduceStockFromWholesaler")
                .queryParam("w_id", w_id)
                .queryParam("p_id", p_id)
                .queryParam("stock", stock)
                .toUriString();
        String s = "";
        System.out.println("\n\t\tPhase-1");
        if(productRetrieved.getStock() >= stock){
            for(Retailer r: rList){
                if(r.getId() == r_id){
                    wList = r.getWholesalerList();
                    for(Wholesaler w: wList){
                        if(w.getId() == w_id){
                            pTempList = w.getProductList();
                            for(Product p: pTempList){
                                if(p.getId() == p_id){
                                    p.setStock(p.getStock() + stock);
                                    s = restTemplate().exchange(urltwo, HttpMethod.PUT,entity, String.class).getBody();
                                    return "Updating existing stocks!!!";
                                }
                            }
                            Product newProduct = new Product(p_id, productRetrieved.getName(), stock, productRetrieved.getPrice(), productRetrieved.getGst());
                            pTempList.add(newProduct);
                            s = restTemplate().exchange(urltwo, HttpMethod.PUT,entity, String.class).getBody();
                            return "Adding new stock t the existing wholesaler";
                        }
                    }
                    Product newProduct = new Product(p_id, productRetrieved.getName(), stock, productRetrieved.getPrice(), productRetrieved.getGst());
                    pTempList.add(newProduct);
                    Wholesaler newWholesaler = new Wholesaler(w_id, wholesalerRetrieved.getName(), pTempList);
                    wList.add(newWholesaler);
                    s = restTemplate().exchange(urltwo, HttpMethod.PUT,entity, String.class).getBody();
                    return "Added new wholesaler and assigned new product!!!";

                }
            }
            return "Retailer not found!!! Enter correct retailer ID";
        }
        return "Try lesser quantity";
    }
}

package com.quinbay.wholesaler.service;


import com.quinbay.wholesaler.functions.Wholesalerinterface;
import com.quinbay.wholesaler.model.Product;
import com.quinbay.wholesaler.model.Wholesaler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.MessageHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Wholesalerservice implements Wholesalerinterface {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Override
    public String addWholesaler(Wholesaler w, List<Wholesaler> wList){
        wList.add(w);
        return "Wholesaler added!!!";
    }

    @Override
    public Wholesaler getWholesaler(int w_id, List<Wholesaler> wList){
        Wholesaler temp = new Wholesaler();
        for(Wholesaler w: wList){
            if(w.getId() == w_id){
                return w;
            }
        }
        return temp;
    }

    @Override
    public String deleteWholesaler(int w_id, List<Wholesaler> wList){
        for(Wholesaler w: wList){
            if(w.getId() == w_id){
                wList.remove(w);
                return "Wholesaler deleted!!!";
            }
        }
        return "Wholesaler not found!!!";
    }

    public Product getProductStock(int w_id, int p_id, List<Wholesaler> wList){
        Product temp = new Product();
        List<Product> pTempList = new ArrayList<>();
        for(Wholesaler w: wList) {
            if (w.getId() == w_id) {
                pTempList = w.getProductList();
                for (Product p : pTempList) {
                    if (p.getId() == p_id) {
                        return p;
                    }
                }
            }
        }
        return temp;
    }

    public String reduceStockFromWholesaler(int w_id, int p_id, int stock, List<Wholesaler> wList){
        List<Product> temp = new ArrayList<>();
        for(Wholesaler w: wList){
            if(w.getId() == w_id){
                temp = w.getProductList();
                for(Product p: temp){
                    if(p.getId() == p_id){
                        p.setStock(p.getStock() - stock);
                    }
                }
            }
        }
        return "Hello from reduceStockFromWholesaler";
    }


    @Override
    public String allocateWholesaler(int w_id, int p_id, int stock, List<Wholesaler> wList){

        List<Product> pList = new ArrayList<>();
        int i;
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/product/getProduct")
                .queryParam("p_id", p_id)
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        String reduceurl = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/product/reduceStock")
                .queryParam("p_id", p_id)
                .queryParam("stock", stock)
                .toUriString();
        Product productRetrieved = restTemplate().getForObject(url, Product.class);

        String s = "";

        if(productRetrieved.getStock() >= stock){
            for(Wholesaler w: wList){
                if(w.getId() == w_id){
                    pList = w.getProductList();
                    for(Product p: pList){
                        if(p.getId() == p_id){
                            p.setStock(p.getStock() + stock);
                            s = restTemplate().exchange(reduceurl, HttpMethod.PUT,entity, String.class).getBody();
                            return "Product stock updated to "+ p.getStock();
                        }
                    }
                    Product newProduct = new Product(p_id, productRetrieved.getName(), stock, productRetrieved.getPrice(), productRetrieved.getGst());
                    pList.add(newProduct);
                    s = restTemplate().exchange(reduceurl, HttpMethod.PUT,entity, String.class).getBody();
                    return "New product added and assigned stock of "+ newProduct.getStock();
                }
            }
            return "Wholesaler not found!!! Enter correct ID !!!";
        }

        return "Try lesser quantity!!!";
    }
}

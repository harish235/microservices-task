package com.quinbay.product.service;

import com.quinbay.product.functions.Productinterface;
import com.quinbay.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements Productinterface {

    @Override
    public String addProduct(Product p, List<Product> pList){
        pList.add(p);
        return "product added to inventory!!!";
    }

    @Override
    public Product getProduct(int id, List<Product> pList){
        Product temp = new Product();
        for(Product p: pList){
            if(p.getId() == id){
                return p;
            }
        }
        return temp;
    }

    public int getStock(int id, List<Product> pList){
        for(Product p: pList){
            if(p.getId() == id){
                return p.getStock();
            }
        }
        return 0;
    }

    public String reduceStock(int id, int stock, List<Product> pList){
        for(Product p: pList){
            if(p.getId() == id){
                p.setStock(p.getStock() - stock);
            }
        }
        return "Hello from reduceStock";
    }

    @Override
    public String updateProduct(int id, int stock, List<Product> pList){
        for(Product p: pList){
            if(p.getId() == id){
                p.setStock(p.getStock() + stock);
                return "stock updated to "+p.getStock();
            }
        }
        return "Product not found!!!";
    }

    @Override
    public String deleteProduct(int id, List<Product> pList){
        for(Product p:pList){
            if(p.getId() == id){
                pList.remove(p);
                return "Product deleted!!!";
            }
        }
        return "Product not found!!!";
    }
}

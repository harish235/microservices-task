package com.quinbay.product.controller;


import com.quinbay.product.model.Product;
import com.quinbay.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    List<Product> productList = new ArrayList<>();

    @Autowired
    ProductService ps;

    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam int p_id){
        return ps.getProduct(p_id, productList);
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product p){
        return ps.addProduct(p, productList);
    }

    @PutMapping("/updateProduct")
    public String updateProduct(@RequestParam int p_id, @RequestParam int stock){
        return ps.updateProduct(p_id, stock, productList);
    }

    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestParam int p_id){
        return ps.deleteProduct(p_id, productList);
    }

    @GetMapping("/getStock")
    public int getStock(@RequestParam int p_id){
        return ps.getStock(p_id, productList);
    }

    @PutMapping("/reduceStock")
    public String reduceStock(@RequestParam int p_id, @RequestParam int stock){
         return ps.reduceStock(p_id, stock,  productList);

    }
}

package com.quinbay.product.functions;

import com.quinbay.product.model.Product;

import java.util.List;

public interface Productinterface {

    String addProduct(Product p, List<Product> pList);
    Product getProduct(int id, List<Product> pList);
    String updateProduct(int id, int stock, List<Product> pList);
    String deleteProduct(int id, List<Product> pList);
}

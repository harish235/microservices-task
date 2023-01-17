package com.quinbay.wholesaler.model;

public class Product {
    int id;
    String name;
    int stock;
    int price;
    int gst;

    public Product(int id, String name, int stock, int price, int gst) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.gst = gst;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getGst() {
        return gst;
    }

    public void setGst(int gst) {
        this.gst = gst;
    }
}


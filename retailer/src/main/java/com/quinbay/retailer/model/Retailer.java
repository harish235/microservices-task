package com.quinbay.retailer.model;

import java.util.List;

public class Retailer {
    int id;
    String name;
    List<Wholesaler> wholesalerList;

    public Retailer(int id, String name, List<Wholesaler> wholesalerList) {
        this.id = id;
        this.name = name;
        this.wholesalerList = wholesalerList;
    }

    public Retailer() {
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

    public List<Wholesaler> getWholesalerList() {
        return wholesalerList;
    }

    public void setWholesalerList(List<Wholesaler> wholesalerList) {
        this.wholesalerList = wholesalerList;
    }
}

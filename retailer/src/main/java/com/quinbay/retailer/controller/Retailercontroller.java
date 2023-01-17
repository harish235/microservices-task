package com.quinbay.retailer.controller;


import com.quinbay.retailer.model.Retailer;
import com.quinbay.retailer.service.Retailerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/Retailer")
@RestController
public class Retailercontroller {
    List<Retailer> retailerList = new ArrayList<>();

    @Autowired
    Retailerservice rs;

    @GetMapping("/getRetailer")
    public Retailer getRetailer(@RequestParam int r_id){
        return rs.getRetailer(r_id, retailerList);
    }

    @PostMapping("/addRetailer")
    public String addRetailer(@RequestBody Retailer r){
        return rs.addRetailer(r, retailerList);
    }

    @PutMapping("/allocateRetailer")
    public String allocateRetailer(@RequestParam int r_id, @RequestParam int w_id, @RequestParam int p_id, @RequestParam int stock){
        return rs.allocateRetailer(r_id, w_id, p_id, stock, retailerList);
    }

    @DeleteMapping("/deleteRetailer")
    public String deleteRetailer(@RequestParam int r_id){
        return rs.deleteRetailer(r_id, retailerList);
    }
}

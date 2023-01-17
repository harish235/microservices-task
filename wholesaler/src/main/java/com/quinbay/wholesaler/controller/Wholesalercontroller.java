package com.quinbay.wholesaler.controller;


import com.quinbay.wholesaler.model.Product;
import com.quinbay.wholesaler.model.Wholesaler;
import com.quinbay.wholesaler.service.Wholesalerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/wholesaler")
@RestController
public class Wholesalercontroller {
    List<Wholesaler> wholesalerList = new ArrayList<>();

    @Autowired
    Wholesalerservice ws;

    @GetMapping("/getWholesaler")
    public Wholesaler getWholesaler(@RequestParam int w_id){
        return ws.getWholesaler(w_id, wholesalerList);
    }

    @PostMapping("/addWholesaler")
    public String addWholesaler(@RequestBody Wholesaler w){
        return ws.addWholesaler(w, wholesalerList);
    }

    @PutMapping("/allocateWholesaler")
    public String allocateWholesaler(@RequestParam int w_id, @RequestParam int p_id, @RequestParam int stock){
        return ws.allocateWholesaler(w_id, p_id, stock, wholesalerList);
    }

    @DeleteMapping("/deleteWholesaler")
    public String deleteWholesaler(@RequestParam int w_id){
        return ws.deleteWholesaler(w_id, wholesalerList);
    }

    @GetMapping("/getWholesalerStock")
    public Product getWholesalerStock(@RequestParam int w_id, @RequestParam int p_id){
        return ws.getProductStock(w_id, p_id, wholesalerList);
    }

    @PutMapping("/reduceStockFromWholesaler")
    public String reduceStockFromWholesaler(@RequestParam int w_id, @RequestParam int p_id, @RequestParam int stock){
        return ws.reduceStockFromWholesaler(w_id, p_id, stock, wholesalerList);
    }
}

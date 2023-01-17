package com.quinbay.retailer.functions;

import com.quinbay.retailer.model.Retailer;

import java.util.List;

public interface Retailerinterface {
    String addRetailer(Retailer r, List<Retailer> rList);
    Retailer getRetailer(int r_id, List<Retailer> rList);
    String allocateRetailer(int r_id, int w_id, int p_id, int stock, List<Retailer> rList);
    String deleteRetailer(int r_id, List<Retailer> rList);
}

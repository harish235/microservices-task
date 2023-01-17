package com.quinbay.wholesaler.functions;

import com.quinbay.wholesaler.model.Wholesaler;

import java.util.List;

public interface Wholesalerinterface {
    String addWholesaler(Wholesaler w, List<Wholesaler> wList);
    Wholesaler getWholesaler(int w_id, List<Wholesaler> wList);
    String allocateWholesaler(int w_id, int p_id, int stock, List<Wholesaler> wList);
    String deleteWholesaler(int w_id, List<Wholesaler> wList);
}

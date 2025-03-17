package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    ArrayList<Merchant> merchants = new ArrayList<>();

    public ArrayList<Merchant> getMerchant() {
        return merchants;
    }

    public boolean addMerchant(Merchant merchant) {
        for (Merchant m:merchants) {
            if (m.getId().equals(merchant.getId()))
                return false;
        }
        merchants.add(merchant);
        return true;
    }

    public boolean updateMerchant(String id, Merchant merchant) {
        for (Merchant m:merchants) {
            if (m.getId().equals(id)) {
                merchants.set(merchants.indexOf(m), merchant);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        for (Merchant m:merchants) {
            if (m.getId().equals(id)) {
                merchants.remove(m);
                return true;
            }
        }
        return false;
    }

    public boolean addProductStock(String productId, String merchantId, int stockAmount){
        return true;
    }

}

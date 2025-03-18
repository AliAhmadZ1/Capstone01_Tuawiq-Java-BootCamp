package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final ProductService productService;
    private final MerchantStockService merchantStockService;
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

    //extra point 2
    // merchant can add offer on his products
    public String addProductStock(String productId, String merchantId, int stockAmount){
        for (Merchant m:merchants){
            if (m.getId().equals(merchantId)){
                for (Product p: productService.getProduct()){
                    if (p.getId().equals(productId)){
                        for (MerchantStock ms:merchantStockService.getMerchantStock()){
                            if (ms.getMerchantId().equals(merchantId)&&ms.getProductId().equals(productId)){
                                ms.setStock(ms.getStock()+stockAmount);
                                return "stocked";
                            }
                        }
                    }
                }
                return "product";
            }
        }
        return "";
    }


    public boolean addProductOffer(String merchantId, double percent){
        boolean addOffer = false;
        for (Merchant m: merchants){
            if (m.getId().equals(merchantId)){
                for (MerchantStock ms: merchantStockService.getMerchantStock()){
                    if (m.getId().equals(ms.getMerchantId())){
                        addOffer = productService.addOffer(ms.getProductId(),percent);
                    }
                }
            }
        }
        return addOffer;
    }


}

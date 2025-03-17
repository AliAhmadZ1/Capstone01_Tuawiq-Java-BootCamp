package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Model.MerchantStock;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    ArrayList<User> users = new ArrayList<>();

    public UserService(MerchantService merchantService, ProductService productService, MerchantStockService merchantStockService) {
        this.merchantService = merchantService;
        this.productService = productService;
        this.merchantStockService = merchantStockService;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String addUser(User user) {
        for (User u : users) {
            if (u.getId().equals(user.getId()))
                return "id";
            else if (u.getUsername().equals(user.getUsername()))
                return "username";
            else if (u.getEmail().equals(user.getEmail()))
                return "email";
        }
        users.add(user);
        return "";
    }

    public String updateUser(String id, User user) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                for (User u2 : users) {
                    if (u2.getUsername().equals(user.getUsername()))
                        return "username";
                    if (u2.getEmail().equals(user.getEmail()))
                        return "email";
                }
                users.set(users.indexOf(u), user);
                return "update";
            }
        }
        return "";
    }

    public boolean deleteUser(String id){
        for (User u:users){
            if (u.getId().equals(id)){
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    // 12- Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.
    public String buyProduct(String userId, String productId, String merchantId){
        for (User u:users){
            if (u.getId().equals(userId)){//user id
                    for (Product p : productService.getProduct()) {
                        if (p.getId().equals(productId)) { //product id
                            for (Merchant m : merchantService.getMerchant()) {
                                if (m.getId().equals(merchantId)) { //merchant id
                                    for (MerchantStock ms:merchantStockService.getMerchantStock()) {
                                        if (ms.getProductId().equals(productId)&&ms.getMerchantId().equals(merchantId)){ //merchant stock
                                            if (ms.getStock()>0){ //check stock
                                                if (u.getBalance()>=p.getPrice()) { //check balance
                                                    ms.setStock(ms.getStock() - 1);
                                                    u.setBalance(u.getBalance() - p.getPrice());
                                                    return "buy";
                                                }
                                                return "less balance";
                                            }
                                            return "no stock";
                                        }
                                    }
                                }
                                return "merchant";
                            }
                        }
                    }
                return "product";
            }
        }
        return "user";
    }


}

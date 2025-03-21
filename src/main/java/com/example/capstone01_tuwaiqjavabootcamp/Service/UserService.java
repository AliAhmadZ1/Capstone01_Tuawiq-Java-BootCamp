package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantService merchantService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;
    private final CategoryService categoryService;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();
    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<User> getUsers(){
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

    public boolean deleteUser(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
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
    public String buyProduct(String userId, String productId, String merchantId) {
        for (User u : users) {
            if (u.getId().equals(userId)) {//user id
                for (Product p : productService.getProduct()) {
                    if (p.getId().equals(productId)) { //product id
                        for (Merchant m : merchantService.getMerchant()) {
                            if (m.getId().equals(merchantId)) { //merchant id
                                for (MerchantStock ms : merchantStockService.getMerchantStock()) {
                                    if (ms.getProductId().equals(productId) && ms.getMerchantId().equals(merchantId)) { //merchant stock
                                        if (ms.getStock() > 0) { //check stock
                                            if (u.getBalance() >= p.getPrice()) { //check balance
                                                ms.setStock(ms.getStock() - 1);
                                                u.setBalance(u.getBalance() - p.getPrice());
                                                merchantService.addBuyer(u,p);
                                                ArrayList<Product> test = new ArrayList<>();
                                                u.setPurchased(test);
                                                u.getPurchased().add(p);
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

    // extra endpoint 1
    // search products by category and price range and availability(stock)

    public ArrayList<String> filterSearch(String categoryName, int min, int max) {

        ArrayList<String> filteredList = new ArrayList<>();
        categories = categoryService.getCategories();
        products = productService.getProduct();
        for (Product p : products) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                for (Category c : categories) {
                    if (c.getName().equalsIgnoreCase(categoryName)) {
                        for (MerchantStock ms : merchantStockService.getMerchantStock()) {
                            if (ms.getStock() >= 1) {
                                String add = "product: " + p.getName() + " with ID: " + p.getId() + ". Merchant ID: " + ms.getMerchantId();
                                filteredList.add(add);
                            }
                        }
                    }
                }
            }
        }
        return filteredList;
    }

    //extra point 3
    // user admin can add tax on products

    public boolean addTax(String id, double tax) {
        if (tax > 0) {
            for (User u : users) {
                if (u.getId().equals(id)) {
                    if (u.getRole().equals("Admin")||u.getRole().equals("Manager")) {
                        return productService.addTax(tax);
                    }
                }
            }
        }
        return false;
    }

    //extra point 3.1
    // user manager can remove tax from products

    public boolean removeTax(String id) {
        for (User u : users) {
            if (u.getId().equals(id)) {
                if (u.getRole().equals("Manager")) {
                    return productService.removeTax();
                }
            }
        }
        return false;
    }

    public String returnProduct(String id, String productId){
        products = productService.getProduct();
        for (User u: users) {
            if (u.getId().equals(id)) {
                for (Product p : u.getPurchased()) {
                    if (p.getId().equals(productId)) {
                        if (merchantService.returnProduct(id, productId)){
                            merchantStockService.updateProductStock(productId);
                            if (p.getOfferPrice()==0)
                                u.setBalance(u.getBalance()+p.getPrice());
                            else
                                u.setBalance(u.getBalance()+p.getOfferPrice());
                            u.getPurchased().remove(p);
                            return "returned";
                        }
                    }
                }
                return "product";
            }
        }
        return "user";
    }


}

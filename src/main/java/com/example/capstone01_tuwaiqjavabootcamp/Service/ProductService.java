package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();


    String[] coupons = {"a12bcd", "34erd"};//1. 15% //2. 50%


    public ArrayList<Product> getProduct() {
        return products;
    }

    public String addProduct(Product product) {
        categories = categoryService.getCategories();
        for (Product p : products) {
            if (p.getId().equals(product.getId()))
                return "id";
        }
        for (Category c : categories) {
            if (c.getId() == product.getCategoryID()) {
                products.add(product);
                return "category";
            }
        }
        return "";
    }

    public boolean updateProduct(String id, Product product) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.set(products.indexOf(p), product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (Product p : products) {
            if (p.getId().equals(id)) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean addOffer(String productId, double percent) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                double afterOffer = p.getPrice() - ((percent / 100) * p.getPrice());
                p.setOfferPrice(afterOffer);
                return true;
            }
        }
        return false;
    }


    //assistant extra point 3

    public boolean addTax(double tax) {
        boolean flag = false;
        for (Product p : products) {
            if (p.getWithTax() == 0) {
                if (p.getOfferPrice() > 0) {
                    double taxed = (p.getOfferPrice() * (tax / 100)) + p.getOfferPrice();
                    p.setWithTax(taxed);
                } else {
                    double taxed = (p.getPrice() * (tax / 100)) + p.getPrice();
                    p.setWithTax(taxed);
                }
                flag = true;
            }
        }
        return flag;
    }

    public boolean removeTax() {
        boolean flag = false;
        for (Product p : products) {
            if (p.getWithTax() != 0) {
                p.setWithTax(0);
                flag = true;
            }
        }
        return flag;
    }

    //extra point 4
    // this apply 15 or 50 percent of discount on product

    public boolean useCoupon(String productId, String coupon) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                if (p.getOfferPrice() != 0)
                    return false;
                if (coupon.equals(coupons[0])) {
                    double discount = p.getPrice() - (p.getPrice() * (0.15));
                    p.setOfferPrice(discount);
                    return true;
                }
                if (coupon.equals(coupons[1])) {
                    double discount = p.getPrice() - (p.getPrice() * (0.5));
                    p.setOfferPrice(discount);
                    return true;
                }
            }
        }
        return false;
    }

    //extra point 5
    // show prices by order using category name

    public ArrayList<String> printByPriceWithCategory(String categoryName) {
        double max = 0;
        ArrayList<String> list = new ArrayList<>();
        for (Category c : categories) {
            for (Product p : products) {
                if (p.getCategoryID() == c.getId()) {
                    if (p.getPrice() > max) {
                        max = p.getPrice();
                        list.add(0, p.getName() + " ," + p.getPrice());
                    } else
                        list.add(p.getName() + " ," + p.getPrice());
                }
            }
        }
        return list;
    }

}



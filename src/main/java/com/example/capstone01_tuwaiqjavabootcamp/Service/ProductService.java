package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final CategoryService categoryService;
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Category> categories = new ArrayList<>();

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
            if (p.getWithTax()!=0) {
                p.setWithTax(0);
                flag = true;
            }
        }
        return flag;
    }

}



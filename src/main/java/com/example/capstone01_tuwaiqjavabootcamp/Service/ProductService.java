package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {

    ArrayList<Product> products = new ArrayList<>();


    public ArrayList<Product> getProduct() {
        return products;
    }

    public boolean addProduct(Product product) {
        for (Product p:products) {
            if (p.getId().equals(product.getId()))
                return false;
        }
        products.add(product);
        return true;
    }

    public boolean updateProduct(String id, Product product) {
        for (Product p:products) {
            if (p.getId().equals(id)) {
                products.set(products.indexOf(p), product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (Product p:products) {
            if (p.getId().equals(id)) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }


}

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
        for (Product p:products) {
            if (p.getId().equals(product.getId()))
                return "id";
        }
        for (Category c:categories){
            if (c.getId()==product.getCategoryID()){
                products.add(product);
                return "category";
            }
        }
        return "";
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

    public void addOffer(String productId, double percent){
        for (Product p:products){
            if (p.getId().equals(productId)){
                double afterOffer = p.getPrice() - ((percent/100)* p.getPrice());
                p.setPrice(afterOffer);
            }
        }
    }

}

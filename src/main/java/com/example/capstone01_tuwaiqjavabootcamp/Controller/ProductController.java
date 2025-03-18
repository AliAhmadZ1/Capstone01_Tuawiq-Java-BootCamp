package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Product;
import com.example.capstone01_tuwaiqjavabootcamp.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    //6- Create endpoint for getting and adding and deleting updating a Product.

    @GetMapping("/get")
    public ResponseEntity getProduct() {
        if (productService.getProduct().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no product"));
        return ResponseEntity.status(200).body(productService.getProduct());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String stringFlag = productService.addProduct(product);
        if (stringFlag.equals("id"))
            return ResponseEntity.status(400).body(new ApiResponse("already exist"));
        else if (stringFlag.equals("category"))
            return ResponseEntity.status(200).body(new ApiResponse("new product is added"));
        return ResponseEntity.status(400).body(new ApiResponse("categoryID is not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (productService.updateProduct(id, product))
            return ResponseEntity.status(200).body(new ApiResponse("product is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        if (productService.deleteProduct(id))
            return ResponseEntity.status(200).body(new ApiResponse("product is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @PutMapping("/coupon/{productId},{coupon}")
    public ResponseEntity useCoupon(@PathVariable String productId,@PathVariable String coupon){
        if (productService.useCoupon(productId, coupon))
            return ResponseEntity.status(200).body(new ApiResponse("coupon is successfully applied"));
        return ResponseEntity.status(400).body(new ApiResponse("not found product of coupon or already product has offer"));
    }

    @GetMapping("/get-category-price/{categoryName}")
    public ResponseEntity getCategoryProductPrices(String categoryName){
        if (productService.printByPriceWithCategory(categoryName).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("not found"));
        return ResponseEntity.status(200).body(productService.printByPriceWithCategory(categoryName));
    }

}

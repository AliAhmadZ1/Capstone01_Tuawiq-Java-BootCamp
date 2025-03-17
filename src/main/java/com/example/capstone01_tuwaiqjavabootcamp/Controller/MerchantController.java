package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Model.Merchant;
import com.example.capstone01_tuwaiqjavabootcamp.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    //8- Create endpoint for getting and adding and deleting updating a Merchant.

    @GetMapping("/get")
    public ResponseEntity getMerchant() {
        if (merchantService.getMerchant().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("there are no merchant"));
        return ResponseEntity.status(200).body(merchantService.getMerchant());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (merchantService.addMerchant(merchant))
            return ResponseEntity.status(200).body(new ApiResponse("new merchant is added"));
        return ResponseEntity.status(400).body(new ApiResponse("already exist"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (merchantService.updateMerchant(id, merchant))
            return ResponseEntity.status(200).body(new ApiResponse("merchant is updated"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        if (merchantService.deleteMerchant(id))
            return ResponseEntity.status(200).body(new ApiResponse("merchant is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }

    //11- Create endpoint where merchant can add more stocks of product to a merchantStock
    //• this endpoint should accept a product id and merchant id and the amount of additional stock.
    @PutMapping("/add-product-stock/{productId},{merchantId},{stockAmount}")
    public ResponseEntity addProductStock(@PathVariable String productId, @PathVariable String merchantId, @PathVariable int stockAmount) {
        if (merchantService.addProductStock(productId, merchantId, stockAmount))
            return ResponseEntity.status(200).body(new ApiResponse("stock of product is added"));
        return ResponseEntity.status(400).body(new ApiResponse("not found"));
    }
}

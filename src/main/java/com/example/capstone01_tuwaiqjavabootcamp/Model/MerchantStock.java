package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    //4- Create MerchantStock Class:
    //• id (must not be empty).
    //• productid (must not be empty)
    //• merchantid (must not be empty).
    //• stock (must not be empty, have to be more than 10 at start).

    @NotEmpty(message = "merchantStock id cannot be empty")
    private String id;
    @NotEmpty(message = "product-id cannot be empty")
    private String productId;
    @NotEmpty(message = "merchant-id cannot be empty")
    private String merchantId;
    @NotNull(message = "stock cannot be null")
    @Min(value = 10,message = "stock have to be at least 10 in first sign")
    private int stock;

}
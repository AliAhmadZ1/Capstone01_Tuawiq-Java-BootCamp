package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    //1- Create Product Class:
    //• id (must not be empty).
    //• name (must not be empty, have to be more than 3 length long).
    //• price (must not be empty, must be positive number).
    //• categoryID (must not be empty).

    @NotEmpty(message = "product id cannot be empty")
    private String id;
    @NotEmpty(message = "product name cannot be empty")
    @Size(min = 3,message = "product name should be more than 3 in length size")
    private String name;
    @NotNull(message = "price cannot be null")
    @Positive(message = "price shouldn't be negative")
    private double price;
    private double offerPrice = 0;
    @NotNull(message = "category ID cannot be null!!")
    private int categoryID;

}

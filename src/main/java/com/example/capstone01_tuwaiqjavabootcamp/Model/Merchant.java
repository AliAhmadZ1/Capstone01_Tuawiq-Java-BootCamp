package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Merchant {

    //3- Create Merchant Class:
        //• id (must not be empty).
        //• name (must not be empty, have to be more than 3 length long).

    @NotEmpty(message = "merchant id cannot be empty")
    private String id;
    @NotEmpty(message = "merchant name cannot be empty")
    @Size(min = 3,message = "merchant name should be more than 3 in length size")
    private String name;
}

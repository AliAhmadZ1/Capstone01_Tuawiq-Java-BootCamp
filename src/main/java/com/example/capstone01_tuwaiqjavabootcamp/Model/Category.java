package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedAttribute;

@Data
@AllArgsConstructor
public class Category {

    //2- Create Category Class:
    // • id (must not be empty).
    // • name (must not be empty, have to be more than 3 length long).

    @NotNull(message = "mustn't be NULL!!")
    @Min(value = 1,message = "category id shouldn't be zero")
    private int id;
    @NotEmpty(message = "category name cannot be empty")
    @Size(min = 3,message = "category name should be more than 3 in length size")
    private String name;

}

package com.example.capstone01_tuwaiqjavabootcamp.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

   //5- Create User Class:
    //• id (must not be empty).
    //• username (must not be empty, have to be more than 5 length long).
    //• password (must not be empty, have to be more than 6 length long, must have
    //characters and digits).
    //• email (must not be empty, must be valid email).
    //• role (must not be empty, have to be in ( “Admin”,”Customer”)).
    //• balance (must not be empty, have to be positive).

    @NotEmpty(message = "User id shouldn't be empty")
    private String id;
    @NotEmpty(message = "username cannot be empty")
    @Size(min = 5,message = "username have to be more than 5 length long")
    private String username;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 6,message = "password have to be more than 6 length long")
    @Pattern(regexp = "^([0-9]|[a-z]|[A-Z])+$",message = "characters and digits only...")
    private String password;
    @NotEmpty(message = "email cannot be empty")
    @Email(/*regexp = "^(\\w([\\w]|[\\d+])+[.|_]*([\\w]|[\\d+])*)@$",*/ message = "should be an email format (example@test)")
    private String email;
    @NotEmpty(message = "role cannot be empty")
    @Pattern(regexp = "^(Admin|Customer|Manager)$",message = "role have to be in (“Admin”,”Customer”)")
    private String role;
    @NotNull(message = "balance cannot be null")
    @PositiveOrZero(message = "balance have to be positive number")
    private double balance;

}

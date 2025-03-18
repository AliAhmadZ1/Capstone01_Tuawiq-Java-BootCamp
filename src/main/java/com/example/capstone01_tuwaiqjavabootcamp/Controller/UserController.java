package com.example.capstone01_tuwaiqjavabootcamp.Controller;

import com.example.capstone01_tuwaiqjavabootcamp.ApiResponse.ApiResponse;
import com.example.capstone01_tuwaiqjavabootcamp.Model.User;
import com.example.capstone01_tuwaiqjavabootcamp.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //10- Create endpoint for getting and adding and deleting updating a User.

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        if (userService.getUsers().isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("There are no users"));
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        if (userService.addUser(user).equals("id"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("ID is already exist"));
        else if (userService.addUser(user).equals("username"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("username is already used"));
        else if (userService.addUser(user).equals("email"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Email is already registered"));

        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("new user is created"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable String id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        String stringFlag = userService.updateUser(id, user);
        if (stringFlag.equals("username"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("username is already used"));
        if (stringFlag.equals("email"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Email is already registered"));
        if (stringFlag.equals("update")) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user is updated"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("Not Found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("user is deleted"));
        return ResponseEntity.status(400).body(new ApiResponse("Not Found"));
    }

// 12- Create endpoint where user can buy a product directly
//• this endpoint should accept user id, product id, merchant id.
//• check if all the given ids are valid or not
//• check if the merchant has the product in stock or return bad request.
//• reduce the stock from the MerchantStock.
//• deducted the price of the product from the user balance.
//• if balance is less than the product price returns bad request.

    @PutMapping("/buy/{userId},{productId},{merchantId}")
    public ResponseEntity buyProduct(@PathVariable String userId,@PathVariable String productId, @PathVariable String merchantId){
        String stringFlag = userService.buyProduct(userId, productId, merchantId);
        if (stringFlag.equals("user"))
            return ResponseEntity.status(400).body(new ApiResponse("user not found"));
        if (stringFlag.equals("product"))
            return ResponseEntity.status(400).body(new ApiResponse("product not found"));
        if (stringFlag.equals("merchant"))
            return ResponseEntity.status(400).body(new ApiResponse("merchant not found"));
        if (stringFlag.equals("no stock"))
            return ResponseEntity.status(400).body(new ApiResponse("the product is out of stock"));
        if (stringFlag.equals("less balance"))
            return ResponseEntity.status(400).body(new ApiResponse("you don't have enough balance to buy."));
        return ResponseEntity.status(200).body(new ApiResponse("successfully purchased"));
    }

    @GetMapping("/search-filter/{categoryName},{min},{max}")
    public ResponseEntity searchFilter(@PathVariable String categoryName, @PathVariable int min,@PathVariable int max){
        if (userService.searchFilter(categoryName, min, max).isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("Can't find results of your search"));
        return ResponseEntity.status(200).body(userService.searchFilter(categoryName, min, max));
    }

    @PutMapping("/add-tax/{userId},{tax}")
    public ResponseEntity addTax(@PathVariable String userId, @PathVariable double tax){
        if (userService.addTax(userId,tax))
            return ResponseEntity.status(200).body(new ApiResponse("Tax added"));
        return ResponseEntity.status(400).body(new ApiResponse("ERROR of: not found, role permission or already signed"));
    }
}






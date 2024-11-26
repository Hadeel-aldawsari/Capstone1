package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "User id should not be empty")
    @Size(min=2,max = 15,message = "User id length should be 2-15 character")
    private String id;

    @NotEmpty(message = "User username should not be empty")
    @Size(min=6,message = "user name should be  more than 5 length long")
    @Pattern(regexp = "^(?=.*[A-Za-z])[A-Za-z0-9._]{4,20}$", message = "Username must contain at least one letter and can only contain alphanumeric characters, dots, or underscores.")
    private String username;

    @NotEmpty(message = "password should not be empty")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{7,}$",
            message = "Password must contain at least one digit [0-9].\n" +
                    "Password must contain at least one lowercase Latin character [a-z].\n" +
                    "Password must contain at least one uppercase Latin character [A-Z].\n" +
                    "Password must contain at least one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 7 characters." )
    private String password;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "enter valid email")
    private String email;

    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "Admin|Customer",message = "role have to be Admin or Customer")
    private String role;


    @NotNull(message = "balance should not be null")
    @PositiveOrZero(message = "balance should be zero or positive")
    private double balance;


    private ArrayList<Order> orders;
    private ArrayList<OrderItem> cartItems;







}

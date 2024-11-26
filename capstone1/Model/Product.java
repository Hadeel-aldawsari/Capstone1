package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    @NotEmpty(message = "Product id should not be empty")
    @Size(min=2,max = 15,message = "id length should be 2-15 character")
    private String id;

    @NotEmpty(message = "Product name should not be empty")
    @Size(min=4,message = "Product name should be more than 3 length long) ")
    private String name;

    @NotNull(message = "Product price should bot be empty")
    @PositiveOrZero(message = "Product price should be zero or positive")
    private double price;

    @NotEmpty(message = "Product catogery id should not be empty")
    private String catogeryID;



}

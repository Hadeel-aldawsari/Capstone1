package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {

    @NotEmpty(message = "Category id should not be empty")
    @Size(min=2,max = 15,message = "Category id length should be 2-15 character")
    private String id;


    @NotEmpty(message = "Category name should not be empty ")
    @Size(min=4,message = "Category name should be more than 3 length long) ")
    private String name;



}

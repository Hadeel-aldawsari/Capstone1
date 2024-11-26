package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    @NotEmpty(message = "Product id should not be empty")
    @Size(min=2,max = 15,message = "id length should be 2-15 character")
    private String id;

    @NotNull(message = "quantity should not be null")
    @PositiveOrZero(message = "quantity should not positive number")
    @Min(value = 1,message = "quantity at least should be 1")
    private int quantity;
}

package com.example.capstone1.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MerchantStock {

    @NotEmpty(message = "MerchantStock id should not be empty")
    @Size(min=2,max = 15,message = "MerchantStock id length should be 2-15 character")
    private String id;

    @NotEmpty(message = "MerchantStock productid should not be empty")
    private String productid;

    @NotEmpty(message = "MerchantStock merchantid should not be empty")
    private String merchantid;

    @NotNull(message = "stock should not be null")
    @PositiveOrZero(message = "stock should be zero or positive")
    @Min(value = 11,message = "stock should be  more than 10 at start")
    private int stock;
}

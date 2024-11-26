package com.example.capstone1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Order {

    @NotEmpty(message = "Orders id should not be empty")
    @Size(min=2,max = 15,message = "Order id length should be 2-15 character")
    private String id;

    @NotEmpty(message = "order item should not be empty")
    private ArrayList<OrderItem> cartItems;

    @NotEmpty(message = "order status should not be empty")
    @Pattern(regexp = "pending|progressing|delivered|refund requested|refunded",message = "order status should be pending|progressing|complete|refund")
    private String OrderStatus;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    @NotEmpty(message = "user id should be empty")
    private String userId;

    @NotNull(message = "total price should no be empty")
    @PositiveOrZero(message = "total price should be positive or zero")
    private double totalPrice;

    @NotEmpty(message = "merchantId should not be empty")
    private String merchantId;





}

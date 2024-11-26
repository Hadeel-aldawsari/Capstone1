package com.example.capstone1.Service;

import com.example.capstone1.Model.OrderItem;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.MerchantStockService;
import com.example.capstone1.Service.ProductService;
import com.example.capstone1.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class CartService {

    private final UserService userService;
    private final ProductService productService;
    private final MerchantStockService merchantStockService;

    public String addToCart(String userId,String merchantId,OrderItem item) {
        User user = userService.getUserByID(userId);
        if (user == null) {
            return "User not found.";
        }

        // check if the product exist
        Product product = productService.getProductByID(item.getId());
        if (product == null) {
            return "Product with id " + item.getId() + " not found.";
        }

        //***important
        if (user.getCartItems() == null) {
            user.setCartItems(new ArrayList<>());
        }

      //check the quantity
        if (merchantStockService.getStock(merchantId, item.getId()) < item.getQuantity()) {
            return "Not enough stock for product " + item.getId();
        }
        if (merchantStockService.getStock(merchantId, item.getId())==0) {
            return "Out Of Stock" + item.getId();
        }

        user.getCartItems().add(item);
        return "Product added to cart successfully!";
    }




  //get customer cart
    public ArrayList<OrderItem> getMyCart(String userId) {
        User user = userService.getUserByID(userId);
        if (user == null) {
            return null;
        }
        return user.getCartItems();
    }


    public double getCartTotal(String userId) {
        //check user exist
        User user = userService.getUserByID(userId);
        if (user == null) {
            return 0.0;
        }
        ArrayList<OrderItem> cartItems = user.getCartItems();
        double total = 0.0;

        for (OrderItem item : cartItems) {
            Product product = productService.getProductByID(item.getId());
            if (product != null) {
                total += product.getPrice() * item.getQuantity();
            }
        }
        return total;
    }


    public boolean clearCart(String userId) {
        User user = userService.getUserByID(userId);
        if (user != null) {
            user.getCartItems().clear();
            return true;
        }
        return false;
    }

}

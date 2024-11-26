package com.example.capstone1.Service;

import com.example.capstone1.Model.Order;
import com.example.capstone1.Model.OrderItem;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private List<Order> orders = new ArrayList<>();
    private final UserService userService;
    private final MerchantStockService merchantStockService;
    private final CartService cartService;

    public String createOrder(String userid, String merchantId) {
    //check user
        User user = userService.getUserByID(userid);
        if (user == null) {
            return "User not found.";
        }

        if(!merchantStockService.isMerchantsStockExist(merchantId))
            return "merchantId not found";
//***important
         if (user.getOrders() == null) {
            user.setOrders(new ArrayList<>());
        }
//         if(cartService.getMyCart(userid)==null || cartService.getMyCart(userid).isEmpty())
//             return "sorry your cart is empty";
       //get cart item
        ArrayList<OrderItem> cartItems = cartService.getMyCart(userid);
        if (cartItems == null || cartItems.isEmpty()) {
            return "Your cart is empty";
        }

        //get total
        double totalPrice = cartService.getCartTotal(userid);

       //check user balance
        if (!userService.checkBalance(userid, totalPrice)) {
            return "User does not have enough balance.";
        }

        //deducted user balance
        userService.deductedBalance(userid, totalPrice);

        // reduce stock
        for (OrderItem item : cartItems) {
            merchantStockService.reduceStock(merchantId, item);
        }

        ArrayList<OrderItem>copyCartItem=new ArrayList<>();
        for(OrderItem item:cartItems){
            copyCartItem.add(item);
        }

        //create order id & order
        String orderId = "ORD-" + (orders.size() + 1);
        Order newOrder = new Order(
                orderId,
                copyCartItem,
                "pending",
                LocalDate.now(),  // تاريخ الطلب يكون اليوم
                userid,
                totalPrice,
                merchantId
        );

       //add order to orders list
        orders.add(newOrder);

     //add order to user list + clear cart
         userService.addOrderToUserList(userid, newOrder);
cartService.clearCart(userid);
        return "Order created successfully with ID: " + orderId;
    }



    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.getId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }
        return null;
    }


    public List<Order> getAllOrders() {
        return orders;
    }


    public ArrayList<Order>getOrdersByStatus(String status){
        ArrayList<Order>orderByStatus=new ArrayList<>();
        for (Order order:orders){
            if(order.getOrderStatus().equalsIgnoreCase(status))
                orderByStatus.add(order);
        }

        return orderByStatus;
    }

}

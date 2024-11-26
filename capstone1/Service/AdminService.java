package com.example.capstone1.Service;

import com.example.capstone1.Model.Order;
import com.example.capstone1.Model.OrderItem;
import com.example.capstone1.Model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final OrderService orderService;
    private final  UserService userService;
private final MerchantStockService merchantStockService;

   //update order status

    public String updateOrderStatus(String adminid,String orderId) {
        if(!userService.getUserByID(adminid).getRole().equalsIgnoreCase("admin"))
            return "No permeation ,only admin can update status";
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            String status = order.getOrderStatus();

            if (status.equals("pending")) {
                order.setOrderStatus("progressing");
                return "Order status changed to progressing";
            } else if (status.equals("progressing")) {
                order.setOrderStatus("delivered");
                return "Order status changed to delivered";
            }
        }
        return "order not found";
    }


    public ArrayList<Order> getRefundRequest(String adminid){
        if(userService.getUserByID(adminid).getRole().equalsIgnoreCase("admin")){
     return orderService.getOrdersByStatus("refund requested");
        }
       return null;
    }


    public String ConfirmRefundRequest(String adminid,String orderid){
        if(!userService.getUserByID(adminid).getRole().equalsIgnoreCase("admin"))
            return "No permeation ,only admin can update status";

        Order order=orderService.getOrderById(orderid);
        if(order==null)return "order not found";
           if(order.getOrderStatus().equalsIgnoreCase("refund requested")){
               ArrayList<OrderItem>orderItems= order.getCartItems();
               for (OrderItem item:orderItems) {
                   merchantStockService.restock(order.getMerchantId(),item.getId(), item.getQuantity());
               }
               userService.rechargeBalance(order.getUserId(),order.getTotalPrice());
               order.setOrderStatus("refunded");
               return "Refund Confirmed successfully";
           }
        return "confirm only for refund requested order";
        }


}

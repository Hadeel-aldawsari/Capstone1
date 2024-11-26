package com.example.capstone1.Service;

import com.example.capstone1.Model.Order;
import com.example.capstone1.Model.OrderItem;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductService productService;
    protected final MerchantStockService merchantStockService;

    private ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }


    public boolean update(String id, User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equalsIgnoreCase(id)) {
                users.set(i, updatedUser);
                return true;
            }
        }
        return false;
    }


    public boolean delete(String id) {
        for (User user : users) {
            if (user.getId().equalsIgnoreCase(id)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }


    public User getUserByID(String id){
        for (User u:users){
            if (u.getId().equalsIgnoreCase(id))
                return u;
        }
        return null;
    }

    public ArrayList<Order> getUserOrder(String userid){
        if(getUserByID(userid).getOrders()==null)return null;
        return getUserByID(userid).getOrders();
    }


    public String refundOrder(String userid,String orderid){
        if(getUserByID(userid)==null)
            return "not found user";
        if(getUserOrder(userid)==null)
          return"there is no order created";
        for (Order o:getUserOrder(userid)){
           if(o.getId().equalsIgnoreCase(orderid) ){
               if(o.getOrderStatus().equalsIgnoreCase("Delivered")) {
                   o.setOrderStatus("refund requested");
               }else {
                   return "refund request should be on  Delivered order only";
               }
            }
        }
        return "refund request created successfully";
    }


public boolean addBalance(String userid,double balance){
        User user=getUserByID(userid);
        if (user!=null && balance>0){
            user.setBalance(user.getBalance()+balance);
            return true;
        }
        return false;
}

    public boolean checkBalance(String userid,double price){
        if(getUserByID(userid).getBalance()>=price)return true;
        return false;
    }

    public void deductedBalance(String userid ,double price){
        getUserByID(userid).setBalance(getUserByID(userid).getBalance()- price);

    }

    public void rechargeBalance(String userid,double balance){
        getUserByID(userid).setBalance(getUserByID(userid).getBalance()+ balance);
    }


    public void addOrderToUserList(String userid,Order order){
        getUserByID(userid).getOrders().add(order);

    }








}

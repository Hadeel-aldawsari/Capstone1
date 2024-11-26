package com.example.capstone1.Service;

import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductService {
    ArrayList<Product> products = new ArrayList<>();
private final CategoryService categoryService;

    public ArrayList<Product> getAll() {
        return products;
    }

    public boolean add(Product product) {
        if (categoryService.isCatogetyExist(product.getCatogeryID())){
            products.add(product);
            return true;
        }
        return false;
    }

    public boolean update(String id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equalsIgnoreCase(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }


    public boolean delete(String id) {
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                products.remove(product);
                return true;
            }
        }
        return false;
    }

    public Product getProductByID(String id){
        for (Product p:products){
            if (p.getId().equalsIgnoreCase(id))
                return p;
        }
        return null;
    }

}

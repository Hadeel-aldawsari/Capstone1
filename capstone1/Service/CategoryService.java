package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    private ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getAll() {
        return categories;
    }

    public void add(Category category) {
        categories.add(category);
    }

    public boolean update(String id, Category updatedCategory) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId().equalsIgnoreCase(id)) {
                categories.set(i, updatedCategory);
                return true;
            }
        }
        return false;
    }

    public boolean delete(String id) {
        for (Category category : categories) {
            if (category.getId().equalsIgnoreCase(id)) {
                categories.remove(category);
                return true;
            }
        }
        return false;
    }

    public boolean isCatogetyExist(String catogeryid){
        for (Category c:categories){
            if(c.getId().equals(catogeryid)){
                return true;
            }
        }
        return false;
    }
}

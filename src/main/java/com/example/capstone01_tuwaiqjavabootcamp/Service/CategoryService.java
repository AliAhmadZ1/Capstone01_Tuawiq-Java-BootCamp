package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public boolean addCategory(Category category) {
        for (Category c : categories) {
            if (c.getId() == category.getId()||c.getName().equals(category.getName()))
                return false;
        }
        categories.add(category);
        return true;
    }

    public boolean updateCategory(int id, Category category) {
        for (Category c : categories) {
            if (c.getId() == id) {
                categories.set(categories.indexOf(c), category);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(int id) {
        for (Category c : categories) {
            if (c.getId() == id) {
                categories.remove(c);
                return true;
            }
        }
        return false;
    }


}

package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Exception.CategoryNotFoundException;
import com.E_COM_App.E_COM_App.Repository.CategoryRepo;
import com.E_COM_App.E_COM_App.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements InterCategoryService{
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category removeCategoryById(Long category_id) {
        return null;
    }

    @Override
    public Category updateCategory(Category category, Long category_id) {
        return null;
    }

    @Override
    public Category getCategoriesById(Long category_id) {
        return categoryRepo.findById(category_id)
                .orElseThrow(()-> new CategoryNotFoundException( "Category not found"));
    }

    @Override
    public Category getCategoriesByName(String categoryName) {
        return categoryRepo.findByName(categoryName);

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}

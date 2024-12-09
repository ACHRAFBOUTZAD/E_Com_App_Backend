package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Request.AddCategoryRequest;
import com.E_COM_App.E_COM_App.model.Category;

import java.util.List;
import java.util.Optional;

public interface InterCategoryService {
    Category addCategory(Category category);
    void removeCategoryById(Long category_id);
    Category updateCategory(Category category,Long category_id);
    Category getCategoriesById(Long category_id);
    Category getCategoriesByName(String categoryName);
    List<Category> getAllCategories();


}
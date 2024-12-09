package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.model.Category;

import java.util.List;

public interface InterCategoryService {
    Category addCategory(Category category);
    Category removeCategoryById(Long category_id);
    Category updateCategory(Category category,Long category_id);
    Category getCategoriesById(Long category_id);
    Category getCategoriesByName(String categoryName);
    List<Category> getAllCategories();


}
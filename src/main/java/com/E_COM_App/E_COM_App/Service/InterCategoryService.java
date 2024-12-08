package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.model.Category;

import java.util.List;

public interface InterCategoryService {
    List<Category> getAllCategories();
    List<Category> getCategoriesById(Long categoryId);
    List<Category> getCategoriesByName(String categoryName);

}
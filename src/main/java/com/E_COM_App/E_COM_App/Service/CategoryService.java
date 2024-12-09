package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Exception.CategoryExistException;
import com.E_COM_App.E_COM_App.Exception.CategoryNotFoundException;
import com.E_COM_App.E_COM_App.Repository.CategoryRepo;
import com.E_COM_App.E_COM_App.Request.AddCategoryRequest;
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
        //check if category already exists if yes return its already exist if no save it
        return Optional.of(category)
                .filter(categoryfilter->!categoryRepo.existsByName(category.getName())).map(categoryRepo::save)
                .orElseThrow(()->
                     new CategoryExistException(category.getName()+"Already Exist in Data Base")
                );
    }


    @Override
    public void removeCategoryById(Long category_id) {
        //search for the category and then remove it else return not found
         categoryRepo.findById(category_id)
                .ifPresentOrElse(categoryRepo::delete,()-> {throw new CategoryNotFoundException("Category not found");});
    }

    @Override
    public Category updateCategory(Category category, Long category_id) {
        //search the category if exist change the name and save it if no return not found
        return Optional.ofNullable(getCategoriesById(category_id))
                .map( OldCategory -> {
                    OldCategory.setName(category.getName());
                    return categoryRepo.save(OldCategory );
                }).orElseThrow(()-> {throw new CategoryNotFoundException("Category not found");});
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

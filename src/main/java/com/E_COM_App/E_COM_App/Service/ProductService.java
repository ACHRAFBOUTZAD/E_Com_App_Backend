package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Exception.ProductNotFoundException;
import com.E_COM_App.E_COM_App.Repository.CategoryRepo;
import com.E_COM_App.E_COM_App.Repository.ImageRepo;
import com.E_COM_App.E_COM_App.Repository.ProductRepo;
import com.E_COM_App.E_COM_App.Request.AddProductRequest;
import com.E_COM_App.E_COM_App.Request.UpdateProductRequest;
import com.E_COM_App.E_COM_App.model.Category;
import com.E_COM_App.E_COM_App.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements InterProductService{
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Product addProduct(AddProductRequest product) {
        //check if the category exist in the database , if no create a new category and give it to the product ,if yes create the product with it
        Category category = Optional.ofNullable(categoryRepo.findByName(product.getCategory().getName()))
                .orElseGet( ()->{
            Category newcategory = new Category(product.getCategory().getName());
            return categoryRepo.save(newcategory);
        } );
        product.setCategory( category);
        return productRepo.save(createProduct(product,category));
    }
    private Product createProduct(AddProductRequest product , Category category){
        return new Product(
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getDescription(),
                product.getInventory(),
                category);
    }

    @Override
    public Product updateProduct(UpdateProductRequest product, long product_id) {
        // if the product exists then just update else throw exception
        return productRepo.findById(product_id)
                .map(existingProduct -> updateExistionProduct(existingProduct, product))
                .map(productRepo :: save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }

    private Product updateExistionProduct(Product existingProduct , UpdateProductRequest updateProduct ){
        //change the old product with the new product
        existingProduct.setName(updateProduct.getName());
        existingProduct.setBrand(updateProduct.getBrand());
        existingProduct.setDescription(updateProduct.getDescription());
        existingProduct.setPrice(updateProduct.getPrice());
        existingProduct.setInventory(updateProduct.getInventory());
        //find the category
        Category category = categoryRepo.findByName(updateProduct.getCategory().getName());
        existingProduct.setCategory( category);
        return existingProduct;

    }

    @Override
    public Product getProductsById(long productId) {
        return productRepo.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void removeProduct(Long product_id) {
        productRepo.findById(product_id)
                        .ifPresentOrElse(productRepo::delete,
                                () -> {throw new ProductNotFoundException("Product not found");});

    }



    @Override
    public void deleteAllProducts() {
        productRepo.deleteAll();

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepo.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepo.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryandBrand(String category, String brand) {
        return productRepo.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String productname) {
        return productRepo.findByName(productname);
    }

    @Override
    public List<Product> getProductsByPrice(BigDecimal price) {
        return productRepo.findByPrice(price);
    }
    @Override
    public List<Product> getProductsByLowerPrice(BigDecimal price) {
        return productRepo.findByLowerPrice(price);
    }
    @Override
    public List<Product> getProductsByHigherPrice(BigDecimal price) {
        return productRepo.findByHigherPrice(price);
    }

    @Override
    public List<Product> getProductsByBetweenPrice(BigDecimal minprice,BigDecimal maxprice) {
        return productRepo.findByBetweenPrice(minprice,maxprice);
    }
    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepo.findByBrandAndName(brand ,name);
    }


    @Override
    public long countProductsByBrandAndName(String brand, String name) {
        return productRepo.countByBrandAndName(brand , name);
    }
}

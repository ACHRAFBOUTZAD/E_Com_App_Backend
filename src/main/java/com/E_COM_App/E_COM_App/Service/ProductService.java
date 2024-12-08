package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Exception.ProductNotFoundException;
import com.E_COM_App.E_COM_App.Repository.CategoryRepo;
import com.E_COM_App.E_COM_App.Repository.ImageRepo;
import com.E_COM_App.E_COM_App.Repository.ProductRepo;
import com.E_COM_App.E_COM_App.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService implements InterProductService{
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductsById(long productId) {
        return productRepo.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    @Override
    public void removeProduct(Long product_id) {
        productRepo.findById(product_id)
                        .ifPresentOrElse(productRepo::delete,
                                () -> {throw new ProductNotFoundException("Product not found");});

    }

    @Override
    public void updateProduct(Product product, long product_id) {

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
    public List<Product> getProductsByHigherPrice(BigDecimal price) {
        return productRepo.findByHigherPrice(price);
    }
    @Override
    public List<Product> getProductsByLowerPrice(BigDecimal price) {
        return productRepo.findByLowerPrice(price);
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

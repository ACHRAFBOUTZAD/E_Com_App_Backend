package com.E_COM_App.E_COM_App.Service;

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
        return null;
    }

    @Override
    public void removeProduct(Product product) {

    }

    @Override
    public void updateProduct(Product product, long product_id) {

    }

    @Override
    public void deleteAllProducts() {

    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return null;
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryandBrand(String category, String brand) {
        return null;
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return null;
    }

    @Override
    public List<Product> getProductsByPrice(BigDecimal price) {
        return null;
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return null;
    }

    @Override
    public long countProductsByBrandAndName(String brand, String name) {
        return 0;
    }
}

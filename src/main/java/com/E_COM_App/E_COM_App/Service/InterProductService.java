package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Request.AddProductRequest;
import com.E_COM_App.E_COM_App.Request.UpdateProductRequest;
import com.E_COM_App.E_COM_App.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface InterProductService {
    Product addProduct(AddProductRequest product);
    void removeProduct(Long product_id);
    Product updateProduct(UpdateProductRequest product, long product_id);
    void deleteAllProducts();
    Product getProductsById (long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductByBrand (String brand);
    List<Product> getProductsByCategoryandBrand(String category,String brand);
    List<Product> getProductsByName (String name);
    List<Product> getProductsByPrice ( BigDecimal price);

    List<Product> getProductsByHigherPrice(BigDecimal price);
    List<Product> getProductsByLowerPrice(BigDecimal price);
    List<Product> getProductsByBetweenPrice(BigDecimal minprice,BigDecimal maxprice);
    List<Product> getProductsByBrandAndName (String brand , String name);
    long countProductsByBrandAndName(String brand,String name);




}

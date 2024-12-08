package com.E_COM_App.E_COM_App.Repository;

import com.E_COM_App.E_COM_App.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {


    List<Product> findByCategoryName(String category);

    List<Product> findByBrand(String brand);

    List<Product> findByCategoryNameAndBrand(String category, String brand);

    List<Product> findByName(String productname);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByBrandAndName(String brand, String name);

    long countByBrandAndName(String brand, String name);

    @Query("SELECT * FROM product p WHERE p.price >= :price")
    List<Product> findByHigherPrice(@Param("price") BigDecimal price);
    @Query("SELECT * FROM product p WHERE p.price <= :price")
    List<Product> findByLowerPrice(@Param("price") BigDecimal price);
    @Query("SELECT * FROM product p WHERE p.price >= :minprice and p.price <= :maxprice ")
    List<Product> findByBetweenPrice(BigDecimal minprice, BigDecimal maxprice);
}

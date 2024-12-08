package com.E_COM_App.E_COM_App.Repository;

import com.E_COM_App.E_COM_App.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {

    Category findByName(String name);
}

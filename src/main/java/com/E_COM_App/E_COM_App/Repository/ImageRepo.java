package com.E_COM_App.E_COM_App.Repository;

import com.E_COM_App.E_COM_App.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image,Long> {
    Image getImageById(Long id);

    Image findByName(String name);

    List<Image> findByFileType(String imageType);

    Image findByDownloadUrl(String imageUrl);
}

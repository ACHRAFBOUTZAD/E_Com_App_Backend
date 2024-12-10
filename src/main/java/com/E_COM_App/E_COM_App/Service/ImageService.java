package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Exception.CategoryNotFoundException;
import com.E_COM_App.E_COM_App.Exception.ImageNotFoundException;
import com.E_COM_App.E_COM_App.Exception.ProductNotFoundException;
import com.E_COM_App.E_COM_App.Repository.ImageRepo;
import com.E_COM_App.E_COM_App.Repository.ProductRepo;
import com.E_COM_App.E_COM_App.Request.AddProductRequest;
import com.E_COM_App.E_COM_App.model.Category;
import com.E_COM_App.E_COM_App.model.Image;
import com.E_COM_App.E_COM_App.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService implements InterImageService {
    @Autowired
    ImageRepo imageRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ProductService productservice;


    @Override
    public Image getImageById(Long id) {
        return Optional.ofNullable(imageRepo.getImageById(id))
                .orElseThrow(()->{throw new ImageNotFoundException("Image Not Found");});
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepo.findByName(name);
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepo.findAll();
    }

    @Override
    public List<Image> getImageByFileType(String imageType) {
        return imageRepo.findByFileType(imageType);
    }

    @Override
    public Image getImageByUrl(String imageUrl) {
        return imageRepo.findByDownloadUrl(imageUrl);
    }

    @Override
    public Image addImage(Image newimage) {

        return null;
    }



    @Override
    public void updateImage(Image updatedimage, Long image_id) {

    }

    @Override
    public void removeAllImage() {
        imageRepo.deleteAll();
    }

    @Override
    public void removeImageById(Long image_id) {
        imageRepo.findById(image_id)
                .ifPresentOrElse(imageRepo::delete,()-> {throw new ImageNotFoundException("Image not found");});
    }

    @Override
    public void removeImageByName(String name) {
        Optional.ofNullable(imageRepo.findByName(name))
                .ifPresentOrElse(imageRepo::delete,()-> {throw new ImageNotFoundException("Image not found");});
    }
}

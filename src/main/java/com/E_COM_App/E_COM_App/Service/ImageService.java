package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Data_to_object.ImageDto;
import com.E_COM_App.E_COM_App.Exception.CategoryNotFoundException;
import com.E_COM_App.E_COM_App.Exception.ImageNotFoundException;
import com.E_COM_App.E_COM_App.Exception.ProductNotFoundException;
import com.E_COM_App.E_COM_App.Repository.ImageRepo;
import com.E_COM_App.E_COM_App.Repository.ProductRepo;
import com.E_COM_App.E_COM_App.model.Category;
import com.E_COM_App.E_COM_App.model.Image;
import com.E_COM_App.E_COM_App.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<ImageDto> saveImages(List<MultipartFile> newfiles, Long product_id) {
        //get the product to associate the images
        Product product = productservice.getProductsById(product_id);
        //create a list of images dto 
        List<ImageDto> savedimagesDto = new ArrayList<ImageDto>();
        for(MultipartFile file : newfiles){
            try {
                //create the new image
                Image image = new Image();
                image.setName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);
                //create a temporary download url that have no image id
                String downloadUrl ="/api/v1/images/image/download/"+image.getId();
                image.setDownloadUrl(downloadUrl);
                //save the image
                Image savedimage = imageRepo.save(image);
                //change the download url attribute after we saved the image and we get the iamge id
                savedimage.setDownloadUrl("/api/v1/images/image/download/"+savedimage.getId());
                //save the new image with the download url complete
                imageRepo.save(savedimage);
                //create image dto 
                ImageDto imageDto = new ImageDto();
                imageDto.setImage_id(savedimage.getId());
                imageDto.setImagename(savedimage.getName());
                imageDto.setDownloadUrl(savedimage.getDownloadUrl());
                //save the imagedto to the list of images dto
                savedimagesDto.add(imageDto);

            } catch (IOException |SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedimagesDto;
    }



    @Override
    public void updateImage(MultipartFile updatedfile, Long image_id) {
        Image image = getImageById(image_id);
        try {
            image.setName(updatedfile.getOriginalFilename());
            image.setFileType(updatedfile.getContentType());
            image.setImage(new SerialBlob(updatedfile.getBytes()));
            imageRepo.save(image);
        } catch (IOException |SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

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

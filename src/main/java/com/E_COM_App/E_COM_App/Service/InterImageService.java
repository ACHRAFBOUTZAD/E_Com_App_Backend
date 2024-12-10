package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.Data_to_object.ImageDto;
import com.E_COM_App.E_COM_App.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InterImageService {

    public Image getImageById(Long id);

    public Image getImageByName(String name);
    public List<Image> getAllImages();
    public List<Image> getImageByFileType(String imageType);
    public Image getImageByUrl(String imageUrl);
    public List<ImageDto> saveImages(List<MultipartFile> file, Long product_id);
    public void updateImage(MultipartFile file, Long image_id);
    public void removeAllImage();
    public void removeImageById(Long id);
    public void removeImageByName(String name);


}

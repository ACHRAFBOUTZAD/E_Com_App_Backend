package com.E_COM_App.E_COM_App.Service;

import com.E_COM_App.E_COM_App.model.Image;

import java.util.List;

public interface InterImageService {

    public Image getImageById(Long id);

    public Image getImageByName(String name);
    public List<Image> getAllImages();
    public List<Image> getImageByFileType(String imageType);
    public Image getImageByUrl(String imageUrl);
    public Image addImage(Image image);
    public void updateImage(Image image,Long image_id);
    public void removeAllImage();
    public void removeImageById(Long id);
    public void removeImageByName(String name);


}

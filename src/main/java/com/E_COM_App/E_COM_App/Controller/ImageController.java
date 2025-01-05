package com.E_COM_App.E_COM_App.Controller;

import com.E_COM_App.E_COM_App.Data_to_object.ImageDto;
import com.E_COM_App.E_COM_App.Service.InterImageService;
import com.E_COM_App.E_COM_App.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private InterImageService imageservice;

    public ResponseEntity<APIResponse> ImagesSave(@RequestParam List<MultipartFile> files, @RequestParam Long ProductId){
        try {
            List<ImageDto> imagesDtos = imageservice.saveImages(files,ProductId);
            return ResponseEntity.ok(new APIResponse("Successfully saved images", imagesDtos));
        }catch(Exception e  ){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new APIResponse(  " Images Save Failed",e.getMessage()) );
        }

    }



}

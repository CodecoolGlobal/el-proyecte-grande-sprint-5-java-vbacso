package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.Image;
import com.codecool.byteMe.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {

    ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource downloadImage(@PathVariable Long imageId) {
        byte[] image = imageService.downloadImage(imageId);
        return new ByteArrayResource(image);
    }

    @PostMapping("/upload")
    public Image saveGroupCoverPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.saveGroupCoverPhoto(file);
    }

}

package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.ImageRepository;
import com.codecool.byteMe.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ImageService {

    ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] downloadImage(Long imageId) {
        return imageRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                .getContent();
    }

    public Image saveGroupCoverPhoto(MultipartFile file) throws IOException {
        // Save to folder
        String fileName = file.getOriginalFilename();
        String absolutePath = FileSystems.getDefault().getPath("src/main/resources/static/").normalize().toAbsolutePath().toString();
        Files.createDirectories(Paths.get(absolutePath + "/uploaded"));
        File newFile = new File(absolutePath + "/uploaded/" + fileName);
        try {
            file.transferTo(newFile);
        } catch (Exception e) {
            System.out.println(e + " path:" + absolutePath);
        }

        // Save to database
        Image image = new Image();
        byte[] content = Files.readAllBytes(newFile.toPath());
        image.setContent(content);
        return imageRepository.save(image);

    }
}

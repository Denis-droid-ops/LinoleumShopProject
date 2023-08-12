package com.kuznetsov.linoleum.service;

import com.kuznetsov.linoleum.util.PropertiesUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

public class ImageService {
    private static final ImageService INSTANCE = new ImageService();
    private final String basePath = PropertiesUtil.get("image.base.url");

    private ImageService(){}

    public void upload(String imagePath, InputStream imageContent) throws IOException {
       Path imageFullPath = Path.of(basePath,imagePath);
       try (imageContent){
           Files.createDirectories(imageFullPath.getParent());
           Files.write(imageFullPath,imageContent.readAllBytes()
                   , StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
       }
    }

    public void deleteImage(String imagePath) throws IOException {
        File file = new File(basePath+imagePath);
        file.delete();
    }

    public Optional<InputStream> get(String imagePath) throws IOException {
        Path imageFullPath = Path.of(basePath,imagePath);
        return Files.exists(imageFullPath) ? Optional.of(Files.newInputStream(imageFullPath))
                :Optional.empty();
    }

    public static ImageService getInstance(){
        return INSTANCE;
    }
}

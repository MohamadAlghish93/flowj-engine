package com.flowengine.service;


import com.flowengine.shared.ConstantsApp;
import com.flowengine.shared.FileStorageException;
import com.flowengine.shared.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private Path fileStorageLocation;

    public FileStorageService() {
        this.fileStorageLocation = Paths.get("./upload")
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private void setPath(String uploadPath){
        this.fileStorageLocation = Paths.get(uploadPath)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String fileName, String uploadPath) {
        //Create path if not exist
        this.setPath(uploadPath);
        // Normalize file name
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        fileName = fileName.replaceAll(" ", "_");
//        fileName = timeStamp + fileName;

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return  fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            this.setPath(ConstantsApp.UPLOAD_PATH);
            Path filePath = this.fileStorageLocation.resolve(fileName.trim()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " +  fileName.trim());
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public void deleteFileAsResource(String fileName) {
        try {
            this.setPath(ConstantsApp.UPLOAD_PATH);
            Path filePath = this.fileStorageLocation.resolve(fileName.trim()).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                if (resource.isFile()){
                    resource.getFile().delete();
                }
            } else {
                throw new MyFileNotFoundException("File not found " +  fileName.trim());
            }
        } catch (Exception ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}

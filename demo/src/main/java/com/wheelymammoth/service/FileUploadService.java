package com.wheelymammoth.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileUploadService {
    
    private static final String UPLOAD_DIR = "uploads/";
    
    static {
        // Create upload directory if it doesn't exist
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create upload directory", e);
        }
    }
    
    public String uploadFile(MultipartFile file, String subdirectory) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        // Generate unique filename
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".") 
            ? originalFilename.substring(originalFilename.lastIndexOf(".")) 
            : "";
        String filename = UUID.randomUUID().toString() + extension;
        
        // Create subdirectory if needed
        Path uploadPath = Paths.get(UPLOAD_DIR + subdirectory);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Save file
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        
        // Return relative URL path
        return "/" + UPLOAD_DIR + subdirectory + "/" + filename;
    }
    
    public void deleteFile(String fileUrl) {
        if (fileUrl != null && fileUrl.startsWith("/")) {
            try {
                Path filePath = Paths.get(fileUrl.substring(1)); // Remove leading /
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                // Log error but don't throw
                System.err.println("Failed to delete file: " + fileUrl);
            }
        }
    }
}


package com.azure.demo.controller;

import com.azure.demo.service.BlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/blob")
public class BlobController {

    private final BlobStorageService blobStorageService;

    @Autowired
    public BlobController(BlobStorageService blobStorageService) {
        this.blobStorageService = blobStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            blobStorageService.upload(
                file.getOriginalFilename(),
                file.getInputStream(),
                file.getSize()
            );
            return ResponseEntity.ok("Uploaded: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }
    @GetMapping("/list")
    public ResponseEntity<?> listBlobs() {
        return ResponseEntity.ok(
            blobStorageService.getContainerClient()
                .listBlobs()
                .stream()
                .map(blob -> blob.getName())
                .toList()
        );
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        try {
            var blobClient = blobStorageService.getContainerClient().getBlobClient(filename);
            var stream = blobClient.openInputStream();
            var bytes = stream.readAllBytes();

            return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                .body(bytes);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
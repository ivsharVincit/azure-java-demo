package com.azure.demo.service;

import com.azure.storage.blob.*;
import com.azure.storage.blob.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.time.OffsetDateTime;
import com.azure.storage.common.StorageSharedKeyCredential;


@Service
public class BlobStorageService {

    private final BlobContainerClient containerClient;

    public BlobStorageService(
        @Value("${azure.storage.account-name}") String accountName,
        @Value("${azure.storage.account-key}") String accountKey,
        @Value("${azure.storage.container-name}") String containerName
    ) {
        String endpoint = String.format("https://%s.blob.core.windows.net", accountName);

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .endpoint(endpoint)
                .credential(new StorageSharedKeyCredential(accountName, accountKey))
                .buildClient();

        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);

        if (!this.containerClient.exists()) {
            this.containerClient.create();
        }
    }

    public void upload(String filename, InputStream data, long size) {
        BlobClient blobClient = containerClient.getBlobClient(filename);
        blobClient.upload(data, size, true);
    }

    public BlobContainerClient getContainerClient() {
        return containerClient;
    }

    public OffsetDateTime getLastModified(String filename) {
        BlobClient blobClient = containerClient.getBlobClient(filename);
        BlobProperties props = blobClient.getProperties();
        return props.getLastModified();
    }
}

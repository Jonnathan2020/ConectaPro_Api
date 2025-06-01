package com.perseus.conectapro.Service;

import com.azure.storage.blob.BlobClient;

import com.azure.storage.blob.BlobContainerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AzureBlobStorageService {

    @Autowired
    private BlobContainerClient containerClient;

    public String uploadImagem(MultipartFile file) throws IOException {
        String nomeArquivo = UUID.randomUUID() + "_" + file.getOriginalFilename();

        BlobClient blobClient = containerClient.getBlobClient(nomeArquivo);
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        // Retorna a URL pública
        return blobClient.getBlobUrl();
    }
}

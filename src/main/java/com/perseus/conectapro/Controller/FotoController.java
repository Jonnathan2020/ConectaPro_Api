package com.perseus.conectapro.controller;

import com.perseus.conectapro.service.AzureBlobStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fotos")
public class FotoController {

    @Autowired
    private AzureBlobStorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFoto(@RequestParam("arquivo") MultipartFile arquivo) {
        try {
            String url = storageService.uploadImagem(arquivo);
            return ResponseEntity.ok(url);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao enviar imagem: " + e.getMessage());
        }
    }
}

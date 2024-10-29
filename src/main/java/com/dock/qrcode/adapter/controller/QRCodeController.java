package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.application.usecase.CreateQRCodeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final CreateQRCodeUseCase createQRCodeUseCase;

    public QRCodeController(CreateQRCodeUseCase createQRCodeUseCase){
        this.createQRCodeUseCase = createQRCodeUseCase;
    }

    @PostMapping("")
    public ResponseEntity<String> createImmediateQRCode(@Validated  @RequestBody QRCodeRequest qrCodeRequest) {
        createQRCodeUseCase.save(qrCodeRequest);
        return ResponseEntity.ok("QR Code cadastrado com sucesso!");
    }

    @GetMapping("")
    public ResponseEntity<List<QRCodeResponse>> getAll(){
        return ResponseEntity.ok(createQRCodeUseCase.qrCodeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(createQRCodeUseCase.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

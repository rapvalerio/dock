package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.application.usecase.CreateQRCodeUseCase;
import com.dock.qrcode.domain.model.QRCode;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> createImmediateQRCode(@Valid @RequestBody QRCodeRequest qrCodeRequest) {
        try{
            QRCodeResponse qrCodeResponse = createQRCodeUseCase.save(qrCodeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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

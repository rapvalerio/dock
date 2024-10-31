package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.application.service.QRCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {
    private final QRCodeService qrCodeService;

    public QRCodeController(QRCodeService qrCodeService){
        this.qrCodeService = qrCodeService;
    }

    @PostMapping("")
    public ResponseEntity<?> createImmediateQRCode(@Valid @RequestBody QRCodeRequest qrCodeRequest) {
        QRCodeResponse qrCodeResponse = qrCodeService.save(qrCodeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
    }

    @GetMapping("")
    public ResponseEntity<List<QRCodeResponse>> getAll(){
        return ResponseEntity.ok(qrCodeService.qrCodeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QRCodeResponse> findById(@PathVariable Long id){
        QRCodeResponse qrCodeResponse = qrCodeService.findById(id);
        return ResponseEntity.ok(qrCodeResponse);
    }
}

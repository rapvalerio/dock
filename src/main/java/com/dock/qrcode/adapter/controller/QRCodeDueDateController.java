package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.dto.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeDueDateResponse;
import com.dock.qrcode.application.service.QRCodeDueDateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qrcode/dueDate")
public class QRCodeDueDateController {

    private final QRCodeDueDateService qrCodeDueDateService;

    public QRCodeDueDateController(QRCodeDueDateService qrCodeDueDateService){
        this.qrCodeDueDateService = qrCodeDueDateService;
    }

    @PostMapping("")
    public ResponseEntity<?> createDueDateQRCode(@Validated @RequestBody QRCodeDueDateRequest qrCodeDueDateRequest){
        try{
            QRCodeDueDateResponse qrCodeResponse = qrCodeDueDateService.save(qrCodeDueDateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("")
    public ResponseEntity<List<QRCodeDueDateResponse>> getAll(){
        return ResponseEntity.ok(qrCodeDueDateService.qrCodeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(qrCodeDueDateService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

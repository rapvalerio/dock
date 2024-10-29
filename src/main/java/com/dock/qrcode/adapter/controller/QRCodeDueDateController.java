package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeDueDateResponse;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.application.usecase.CreateQRCodeDueDateUseCase;
import com.dock.qrcode.application.usecase.CreateQRCodeUseCase;
import com.dock.qrcode.domain.model.QRCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qrcode/dueDate")
public class QRCodeDueDateController {

    private final CreateQRCodeDueDateUseCase createQRCodeDueDateUseCase;

    public QRCodeDueDateController(CreateQRCodeDueDateUseCase createQRCodeDueDateUseCase){
        this.createQRCodeDueDateUseCase = createQRCodeDueDateUseCase;
    }

    @PostMapping("")
    public ResponseEntity<String> createDueDateQRCode(@Validated @RequestBody QRCodeDueDateRequest qrCodeDueDateRequest){
        createQRCodeDueDateUseCase.save(qrCodeDueDateRequest);
        return ResponseEntity.ok("QR Code gerado com sucesso");
    }

    @GetMapping("")
    public ResponseEntity<List<QRCodeDueDateResponse>> getAll(){
        return ResponseEntity.ok(createQRCodeDueDateUseCase.qrCodeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(createQRCodeDueDateUseCase.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

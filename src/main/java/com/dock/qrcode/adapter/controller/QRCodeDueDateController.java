package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeDueDateResponse;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.application.usecase.CreateQRCodeDueDateUseCase;
import com.dock.qrcode.application.usecase.CreateQRCodeUseCase;
import com.dock.qrcode.domain.model.QRCode;
import com.dock.qrcode.domain.model.QRCodeDueDate;
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
    public ResponseEntity<?> createDueDateQRCode(@Validated @RequestBody QRCodeDueDateRequest qrCodeDueDateRequest){
        try{
            QRCodeDueDateResponse qrCodeResponse = createQRCodeDueDateUseCase.save(qrCodeDueDateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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

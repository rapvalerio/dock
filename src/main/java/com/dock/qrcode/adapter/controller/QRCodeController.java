package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.application.service.QRCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria um QR Code imediato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "QR Code criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> createImmediateQRCode(@Valid @RequestBody QRCodeRequest qrCodeRequest) {
        QRCodeResponse qrCodeResponse = qrCodeService.save(qrCodeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
    }

    @Operation(summary = "Lista todos os QR Codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de QR Codes retornada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeResponse.class))})
    })
    @GetMapping("")
    public ResponseEntity<List<QRCodeResponse>> getAll(){
        return ResponseEntity.ok(qrCodeService.qrCodeList());
    }

    @Operation(summary = "Busca um QR Code pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "QR Code retornado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeResponse.class))}),
            @ApiResponse(responseCode = "404", description = "QR Code não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<QRCodeResponse> findById(@PathVariable Long id){
        QRCodeResponse qrCodeResponse = qrCodeService.findById(id);
        return ResponseEntity.ok(qrCodeResponse);
    }
}

package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.dto.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeDueDateResponse;
import com.dock.qrcode.application.service.QRCodeDueDateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Cria um QR Code com data de vencimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "QR Code criado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeDueDateResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping("")
    public ResponseEntity<?> createDueDateQRCode(@Validated @RequestBody QRCodeDueDateRequest qrCodeDueDateRequest){
        try{
            QRCodeDueDateResponse qrCodeResponse = qrCodeDueDateService.save(qrCodeDueDateRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(qrCodeResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista todos os QR Codes com data de vencimento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de QR Codes retornada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeDueDateResponse.class))})
    })
    @GetMapping("")
    public ResponseEntity<List<QRCodeDueDateResponse>> getAll(){
        return ResponseEntity.ok(qrCodeDueDateService.qrCodeList());
    }

    @Operation(summary = "Busca um QR Code com data de vencimento pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "QR Code retornado com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = QRCodeDueDateResponse.class))}),
            @ApiResponse(responseCode = "404", description = "QR Code não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(qrCodeDueDateService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

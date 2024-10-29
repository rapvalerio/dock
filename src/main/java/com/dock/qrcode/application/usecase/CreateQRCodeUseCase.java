package com.dock.qrcode.application.usecase;

import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.domain.model.QRCode;
import com.dock.qrcode.infrastructure.H2QRCodeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreateQRCodeUseCase {
    private final H2QRCodeRepository qrCodeRepository;

    public CreateQRCodeUseCase(H2QRCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public void save(QRCodeRequest qrCodeRequest){
        QRCode qrCode = this.toEntity(qrCodeRequest);
        this.qrCodeRepository.save(qrCode);
    }

    public List<QRCodeResponse> qrCodeList(){
        List<QRCode> qrCodes = new ArrayList<>();
        this.qrCodeRepository.findAll().forEach(qrCodes::add);

        return qrCodes.stream()
                .map(this::toResponse)
                .toList();
    }

    public QRCodeResponse findById(Long id) throws Exception {
        QRCode qrCode = this.qrCodeRepository.findById(id)
                .orElseThrow(() -> new Exception("Registro nao encontrado"));

        return toResponse(qrCode);
    }

    private QRCode toEntity(QRCodeRequest qrCodeRequest){
        QRCode qrCode = new QRCode();
        qrCode.setDescription(qrCodeRequest.getDescription());
        qrCode.setAmount(qrCodeRequest.getAmount());
        qrCode.setDueDate(qrCodeRequest.getDueDate());
        qrCode.setStatus(qrCodeRequest.getStatus());
        return qrCode;
    }

    private QRCodeResponse toResponse(QRCode qrCode) {
        QRCodeResponse response = new QRCodeResponse();
        response.setId(qrCode.getId());
        response.setDescription(qrCode.getDescription());
        response.setAmount(qrCode.getAmount());
        response.setDueDate(qrCode.getDueDate());
        response.setStatus(qrCode.getStatus());
        return response;
    }
}

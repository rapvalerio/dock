package com.dock.qrcode.application.usecase;

import com.dock.qrcode.adapter.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.response.QRCodeDueDateResponse;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.domain.model.QRCode;
import com.dock.qrcode.domain.model.QRCodeDueDate;
import com.dock.qrcode.infrastructure.H2QRCodeDueDateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateQRCodeDueDateUseCase {
    private final H2QRCodeDueDateRepository qrCodeDueDateRepository;

    public CreateQRCodeDueDateUseCase(H2QRCodeDueDateRepository qrCodeDueDateRepository) {
        this.qrCodeDueDateRepository = qrCodeDueDateRepository;
    }

    public void save(QRCodeDueDateRequest qrCodeDueDateRequest){
        QRCodeDueDate qrCodeDueDate = this.toEntity(qrCodeDueDateRequest);
        this.qrCodeDueDateRepository.save(qrCodeDueDate);
    }

    public List<QRCodeDueDateResponse> qrCodeList(){
        List<QRCodeDueDate> qrCodes = new ArrayList<>();
        this.qrCodeDueDateRepository.findAll().forEach(qrCodes::add);

        return qrCodes.stream()
                .map(this::toResponse)
                .toList();
    }

    public QRCodeDueDateResponse findById(Long id) throws Exception {
        QRCodeDueDate qrCodeDueDate = this.qrCodeDueDateRepository.findById(id)
                .orElseThrow(() -> new Exception("Registro nao encontrado"));

        return toResponse(qrCodeDueDate);
    }

    private QRCodeDueDate toEntity(QRCodeDueDateRequest qrCodeDueDateRequest){
        QRCodeDueDate qrCodeDueDate = new QRCodeDueDate();
        qrCodeDueDate.setDescription(qrCodeDueDateRequest.getDescription());
        qrCodeDueDate.setAmount(qrCodeDueDateRequest.getAmount());
        qrCodeDueDate.setDueDate(qrCodeDueDateRequest.getDueDate());
        qrCodeDueDate.setStatus(qrCodeDueDateRequest.getStatus());
        return qrCodeDueDate;
    }

    private QRCodeDueDateResponse toResponse(QRCodeDueDate qrCodeDueDate) {
        QRCodeDueDateResponse response = new QRCodeDueDateResponse();
        response.setId(qrCodeDueDate.getId());
        response.setDescription(qrCodeDueDate.getDescription());
        response.setAmount(qrCodeDueDate.getAmount());
        response.setDueDate(qrCodeDueDate.getDueDate());
        response.setStatus(qrCodeDueDate.getStatus());
        return response;
    }
}

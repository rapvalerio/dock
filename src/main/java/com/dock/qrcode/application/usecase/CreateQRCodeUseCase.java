package com.dock.qrcode.application.usecase;

import com.dock.qrcode.adapter.request.QRCodeRequest;
import com.dock.qrcode.adapter.response.QRCodeResponse;
import com.dock.qrcode.domain.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateQRCodeUseCase extends AbstractQRCodeUseCase<QRCodeRequest, QRCodeResponse, QRCode> {
    protected CreateQRCodeUseCase(JpaRepository<QRCode, Long> repository) {
        super(repository);
    }

    @Override
    protected QRCode toEntity(QRCodeRequest request) {
        QRCode qrCode = new QRCode();
        qrCode.setDescription(request.getDescription());
        qrCode.setAmount(request.getAmount());
        qrCode.setStatus(request.getStatus());
        qrCode.setChangeableAmount(request.getChangeableAmount());
        qrCode.setExpiredDate(request.getExpiredDate());
        return qrCode;
    }

    @Override
    protected QRCodeResponse toResponse(QRCode entity) {
        QRCodeResponse response = new QRCodeResponse();
        response.setId(entity.getId());
        response.setDescription(entity.getDescription());
        response.setAmount(entity.getAmount());
        response.setStatus(entity.getStatus());
        response.setExpiredDate(entity.getExpiredDate());
        response.setChangeableAmount(entity.getChangeableAmount());
        return response;
    }
}

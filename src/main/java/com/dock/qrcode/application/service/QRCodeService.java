package com.dock.qrcode.application.service;

import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.domain.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class QRCodeService extends BaseQRCodeService<QRCodeRequest, QRCodeResponse, QRCode> {
    protected QRCodeService(JpaRepository<QRCode, Long> repository) {
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

package com.dock.qrcode.application.service;

import com.dock.qrcode.adapter.dto.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeDueDateResponse;
import com.dock.qrcode.domain.model.QRCodeDueDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class QRCodeDueDateService extends BaseQRCodeService<QRCodeDueDateRequest, QRCodeDueDateResponse, QRCodeDueDate> {
    protected QRCodeDueDateService(JpaRepository<QRCodeDueDate, Long> repository) {
        super(repository);
    }

    @Override
    protected QRCodeDueDate toEntity(QRCodeDueDateRequest request) {
        QRCodeDueDate qrCodeDueDate = new QRCodeDueDate();
        qrCodeDueDate.setDescription(request.getDescription());
        qrCodeDueDate.setAmount(request.getAmount());
        qrCodeDueDate.setDueDate(request.getDueDate());
        qrCodeDueDate.setStatus(request.getStatus());
        qrCodeDueDate.setExpiredDate(request.getExpiredDate());
        qrCodeDueDate.setChangeableAmount(request.getChangeableAmount());
        qrCodeDueDate.setInterest(request.getInterest());
        return qrCodeDueDate;
    }

    @Override
    protected QRCodeDueDateResponse toResponse(QRCodeDueDate entity) {
        QRCodeDueDateResponse response = new QRCodeDueDateResponse();
        response.setId(entity.getId());
        response.setDescription(entity.getDescription());
        response.setAmount(entity.getAmount());
        response.setDueDate(entity.getDueDate());
        response.setStatus(entity.getStatus());
        response.setExpiredDate(entity.getExpiredDate());
        response.setChangeableAmount(entity.getChangeableAmount());
        response.setInterest(entity.getInterest());
        return response;
    }
}

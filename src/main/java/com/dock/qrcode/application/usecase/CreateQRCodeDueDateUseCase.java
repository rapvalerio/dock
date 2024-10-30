package com.dock.qrcode.application.usecase;

import com.dock.qrcode.adapter.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.response.QRCodeDueDateResponse;
import com.dock.qrcode.domain.model.QRCodeDueDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateQRCodeDueDateUseCase extends AbstractQRCodeUseCase<QRCodeDueDateRequest, QRCodeDueDateResponse, QRCodeDueDate> {
    protected CreateQRCodeDueDateUseCase(JpaRepository<QRCodeDueDate, Long> repository) {
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
        return response;
    }
}

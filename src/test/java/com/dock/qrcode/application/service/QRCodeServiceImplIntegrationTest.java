package com.dock.qrcode.application.service;

import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.application.exception.QRCodeNotFoundException;
import com.dock.qrcode.infrastructure.H2QRCodeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class QRCodeServiceImplIntegrationTest {

    @Autowired
    private QRCodeService service;

    @Autowired
    private H2QRCodeRepository repository;

    @Test
    void save_shouldPersistQRCode() {
        // Arrange
        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setDescription("Test QR Code");
        qrCodeRequest.setAmount(100.0);
        qrCodeRequest.setStatus("new");
        qrCodeRequest.setChangeableAmount(true);

        // Act
        QRCodeResponse qrCodeResponse = service.save(qrCodeRequest);

        // Assert
        assertNotNull(qrCodeResponse);
        assertNotNull(qrCodeResponse.getId());
        assertEquals("Test QR Code", qrCodeResponse.getDescription());
        assertEquals(100.0, qrCodeResponse.getAmount());
        assertEquals("new", qrCodeResponse.getStatus());
        assertTrue(qrCodeResponse.getChangeableAmount());
    }

    @Test
    void qrCodeList_shouldReturnAllQRCodes() {
        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setDescription("Test QR Code");
        qrCodeRequest.setAmount(100.0);
        qrCodeRequest.setStatus("new");
        qrCodeRequest.setChangeableAmount(true);

        QRCodeRequest qrCodeRequest2 = new QRCodeRequest();
        qrCodeRequest2.setDescription("Test QR Code");
        qrCodeRequest2.setAmount(100.0);
        qrCodeRequest2.setStatus("new");
        qrCodeRequest2.setChangeableAmount(true);


        QRCodeResponse qrCodeResponse = service.save(qrCodeRequest);
        QRCodeResponse qrCodeResponse2 = service.save(qrCodeRequest2);
        List<QRCodeResponse> qrCodeResponseList = service.qrCodeList();
        assertEquals(2, qrCodeResponseList.size());
    }
    @Test
    void findById_shouldReturnQRCodeIfExists() {
        QRCodeRequest qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setDescription("Test QR Code");
        qrCodeRequest.setAmount(100.0);
        qrCodeRequest.setStatus("new");
        qrCodeRequest.setChangeableAmount(true);
        QRCodeResponse qrCodeResponse = service.save(qrCodeRequest);

        QRCodeResponse foundQRCode = service.findById(qrCodeResponse.getId());

        assertNotNull(foundQRCode);
        assertEquals(qrCodeResponse.getId(), foundQRCode.getId());
    }


    @Test
    void findById_shouldThrowExceptionIfNotFound() {
        assertThrows(QRCodeNotFoundException.class, () -> service.findById(999L));
    }
}

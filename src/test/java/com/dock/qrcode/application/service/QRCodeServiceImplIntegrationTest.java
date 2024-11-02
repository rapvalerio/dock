package com.dock.qrcode.application.service;

import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.application.exception.QRCodeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class QRCodeServiceImplIntegrationTest {

    @Autowired
    private QRCodeService service;

    private QRCodeRequest qrCodeRequest;
    private QRCodeRequest qrCodeRequest2;
    private LocalDateTime dataTest;

    @BeforeEach
    void setUp() {
        dataTest = LocalDateTime.now();

        qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setDescription("Test QR Code");
        qrCodeRequest.setAmount(100.0);
        qrCodeRequest.setStatus("new");
        qrCodeRequest.setChangeableAmount(true);
        qrCodeRequest.setExpiredDate(dataTest.plusDays(1));

        qrCodeRequest2 = new QRCodeRequest();
        qrCodeRequest2.setDescription("Test QR Code");
        qrCodeRequest2.setAmount(100.0);
        qrCodeRequest2.setStatus("new");
        qrCodeRequest2.setChangeableAmount(true);
        qrCodeRequest.setExpiredDate(dataTest.plusDays(1));
    }

    @Test
    void save_shouldPersistQRCode() {
        QRCodeResponse qrCodeResponse = service.save(qrCodeRequest);

        assertNotNull(qrCodeResponse);
        assertNotNull(qrCodeResponse.getId());
        assertEquals("Test QR Code", qrCodeResponse.getDescription());
        assertEquals(100.0, qrCodeResponse.getAmount());
        assertEquals("new", qrCodeResponse.getStatus());
        assertEquals(dataTest.plusDays(1), qrCodeResponse.getExpiredDate());
        assertTrue(qrCodeResponse.getChangeableAmount());
    }

    @Test
    void qrCodeList_shouldReturnAllQRCodes() {
        service.save(qrCodeRequest);
        service.save(qrCodeRequest2);
        List<QRCodeResponse> qrCodeResponseList = service.qrCodeList();

        assertEquals(2, qrCodeResponseList.size());
    }
    @Test
    void findById_shouldReturnQRCodeIfExists() {
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

package com.dock.qrcode.application.service;

import com.dock.qrcode.adapter.dto.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeDueDateResponse;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import com.dock.qrcode.application.exception.QRCodeNotFoundException;
import com.dock.qrcode.infrastructure.H2QRCodeDueDateRepository;
import com.dock.qrcode.infrastructure.H2QRCodeRepository;
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
public class QRCodeDueDateServiceImplIntegrationTest {

    @Autowired
    private QRCodeDueDateService service;

    private QRCodeDueDateRequest qrCodeDueDateRequest;
    private QRCodeDueDateRequest qrCodeDueDateRequest2;
    private LocalDateTime dataTest;

    @BeforeEach
    void setUp() {
        dataTest = LocalDateTime.now();

        qrCodeDueDateRequest = new QRCodeDueDateRequest();
        qrCodeDueDateRequest.setDescription("Test QR Code DueDate");
        qrCodeDueDateRequest.setAmount(100.0);
        qrCodeDueDateRequest.setStatus("new");
        qrCodeDueDateRequest.setChangeableAmount(true);
        qrCodeDueDateRequest.setInterest(2.0);
        qrCodeDueDateRequest.setExpiredDate(dataTest.plusDays(1));
        qrCodeDueDateRequest.setDueDate(dataTest.plusDays(2));

        qrCodeDueDateRequest2 = new QRCodeDueDateRequest();
        qrCodeDueDateRequest2.setDescription("Test QR Code DueDate 2");
        qrCodeDueDateRequest2.setAmount(100.0);
        qrCodeDueDateRequest2.setStatus("new");
        qrCodeDueDateRequest2.setChangeableAmount(true);
        qrCodeDueDateRequest2.setInterest(2.0);
        qrCodeDueDateRequest2.setExpiredDate(dataTest.plusDays(1));
        qrCodeDueDateRequest2.setDueDate(dataTest.plusDays(2));
    }

    @Test
    void save_shouldPersistQRCode() {
        QRCodeDueDateResponse qrCodeDueDateResponse = service.save(qrCodeDueDateRequest);

        assertNotNull(qrCodeDueDateResponse);
        assertNotNull(qrCodeDueDateResponse.getId());
        assertEquals("Test QR Code DueDate", qrCodeDueDateResponse.getDescription());
        assertEquals(100.0, qrCodeDueDateResponse.getAmount());
        assertEquals("new", qrCodeDueDateResponse.getStatus());
        assertEquals(2.0, qrCodeDueDateResponse.getInterest());
        assertEquals(dataTest.plusDays(1), qrCodeDueDateResponse.getExpiredDate());
        assertEquals(dataTest.plusDays(2), qrCodeDueDateResponse.getDueDate());
        assertTrue(qrCodeDueDateResponse.getChangeableAmount());
    }

    @Test
    void qrCodeList_shouldReturnAllQRCodes() {
        service.save(qrCodeDueDateRequest);
        service.save(qrCodeDueDateRequest2);

        List<QRCodeDueDateResponse> qrCodeResponseList = service.qrCodeList();
        assertEquals(2, qrCodeResponseList.size());
    }
    @Test
    void findById_shouldReturnQRCodeIfExists() {
        QRCodeDueDateResponse qrCodeDueDateResponse = service.save(qrCodeDueDateRequest);
        QRCodeDueDateResponse foundQRCode = service.findById(qrCodeDueDateResponse.getId());

        assertNotNull(foundQRCode);
        assertEquals(qrCodeDueDateResponse.getId(), foundQRCode.getId());
    }


    @Test
    void findById_shouldThrowExceptionIfNotFound() {
        assertThrows(QRCodeNotFoundException.class, () -> service.findById(999L));
    }
}

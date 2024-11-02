package com.dock.qrcode.adapter.controller;

import com.dock.qrcode.adapter.dto.request.QRCodeDueDateRequest;
import com.dock.qrcode.adapter.dto.request.QRCodeRequest;
import com.dock.qrcode.adapter.dto.response.QRCodeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QRCodeControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    private QRCodeRequest qrCodeRequest;
    private LocalDateTime dataTest;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/qrcode";

        dataTest = LocalDateTime.now();

        qrCodeRequest = new QRCodeRequest();
        qrCodeRequest.setAmount(150.0);
        qrCodeRequest.setChangeableAmount(true);
        qrCodeRequest.setExpiredDate(LocalDateTime.now().plusDays(1));
    }

    @Test
    void whenCreateQRCode_thenStatusCreated() {
        ResponseEntity<QRCodeResponse> response = restTemplate.postForEntity(baseUrl, qrCodeRequest, QRCodeResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAmount()).isEqualTo(150.0);
    }

    @Test
    void whenGetAllQRCodes_thenReturnList() {
        restTemplate.postForEntity(baseUrl, qrCodeRequest, QRCodeResponse.class);

        ResponseEntity<QRCodeResponse[]> response = restTemplate.getForEntity(baseUrl, QRCodeResponse[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().length).isGreaterThan(0);
    }

    @Test
    void whenCreateQRCodeWithInvalidDate_thenReturnBadRequest() {
        qrCodeRequest.setExpiredDate(dataTest.minusDays(1));

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, qrCodeRequest, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Data deve ser maior que data atual");
    }

    @Test
    void whenFindById_thenReturnQRCode() {
        ResponseEntity<QRCodeResponse> postResponse = restTemplate.postForEntity(baseUrl, qrCodeRequest, QRCodeResponse.class);
        Long id = postResponse.getBody().getId();

        ResponseEntity<QRCodeResponse> getResponse = restTemplate.getForEntity(baseUrl + "/" + id, QRCodeResponse.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getId()).isEqualTo(id);
    }

    @Test
    void whenFindByInvalidId_thenReturnNotFound() {
        ResponseEntity<QRCodeResponse> response = restTemplate.getForEntity(baseUrl + "/999", QRCodeResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}

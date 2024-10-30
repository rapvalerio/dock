package com.dock.qrcode.adapter.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class QRCodeBaseResponse {
    private Long id;
    private String description;
    private double amount;
    private LocalDateTime expiredDate;
    private Boolean changeableAmount;
    private String status;
}

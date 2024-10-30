package com.dock.qrcode.adapter.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QRCodeDueDateResponse extends QRCodeBaseResponse {
    private LocalDateTime dueDate;
    private Double interest;
}

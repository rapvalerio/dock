package com.dock.qrcode.adapter.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class QRCodeBaseRequest {
    @NotNull(message = "Campo obrigatorio")
    @Positive(message = "Valor deve ser maior que 0")
    private double amount;

    @NotNull(message = "Campo obrigatorio")
    @Future(message = "Data deve ser maior que data atual")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;

    @NotNull(message = "Campo obrigatorio")
    private Boolean changeableAmount;

    private String description;
    private String status;
}

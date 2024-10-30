package com.dock.qrcode.adapter.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QRCodeDueDateRequest extends QRCodeBaseRequest {
    @NotNull(message = "Campo obrigatorio")
    @Future
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;

    @NotNull(message = "Campo obrigatorio")
    @Positive(message = "Valor precisa ser maior que 0")
    private Double interest;
}

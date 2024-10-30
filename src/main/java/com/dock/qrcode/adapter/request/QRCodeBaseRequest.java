package com.dock.qrcode.adapter.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public abstract class QRCodeBaseRequest {
    @Positive(message = "Valor deve ser maior que 0")
    private double amount;

    @Future(message = "Data deve ser maior que data atual")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dueDate;
    @Future(message = "Data deve ser maior que data atual")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expiredDate;
    private Boolean changeableAmount;
    private String description;
    private String status;

    @Positive(message = "Valor deve ser maior que 0")
    public double getAmount() {
        return amount;
    }

    public void setAmount(@Positive(message = "Valor deve ser maior que 0") double amount) {
        this.amount = amount;
    }

    public Boolean getChangeableAmount() {
        return changeableAmount;
    }

    public void setChangeableAmount(Boolean changeableAmount) {
        this.changeableAmount = changeableAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @Future(message = "Data deve ser maior que data atual") LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(@Future(message = "Data deve ser maior que data atual") LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public @Future(message = "Data deve ser maior que data atual") LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(@Future(message = "Data deve ser maior que data atual") LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.dock.qrcode.adapter.response;

import java.time.LocalDateTime;

public abstract class QRCodeBaseResponse {
    private Long id;
    private String description;
    private double amount;
    private LocalDateTime expiredDate;
    private Boolean changeableAmount;
    private String status;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

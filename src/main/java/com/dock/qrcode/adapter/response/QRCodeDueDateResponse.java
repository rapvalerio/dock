package com.dock.qrcode.adapter.response;

import java.time.LocalDateTime;

public class QRCodeDueDateResponse extends QRCodeBaseResponse {
    private LocalDateTime dueDate;

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}

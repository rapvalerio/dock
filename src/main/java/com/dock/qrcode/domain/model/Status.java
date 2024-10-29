package com.dock.qrcode.domain.model;

public enum Status {
    NEW("new"),
    CANCELED("canceled"),
    PAID("paid"),
    EXPIRED("expired");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

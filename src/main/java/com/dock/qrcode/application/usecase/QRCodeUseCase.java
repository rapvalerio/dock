package com.dock.qrcode.application.usecase;

import java.util.List;

public interface QRCodeUseCase<T, R>{
    void save(T request);
    List<R> qrCodeList();
    R findById(Long id) throws Exception;
}

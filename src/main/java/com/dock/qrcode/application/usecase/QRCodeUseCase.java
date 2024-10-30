package com.dock.qrcode.application.usecase;

import java.util.List;

public interface QRCodeUseCase<Request, Response>{
    Response save(Request request);
    List<Response> qrCodeList();
    Response findById(Long id) throws Exception;
}

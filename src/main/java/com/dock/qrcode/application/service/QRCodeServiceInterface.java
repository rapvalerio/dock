package com.dock.qrcode.application.service;

import java.util.List;

public interface QRCodeServiceInterface<Request, Response>{
    Response save(Request request);
    List<Response> qrCodeList();
    Response findById(Long id) throws Exception;
}

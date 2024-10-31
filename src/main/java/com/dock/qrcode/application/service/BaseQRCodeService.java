package com.dock.qrcode.application.service;

import com.dock.qrcode.application.exception.QRCodeNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseQRCodeService<Request, Response, Model> implements QRCodeServiceInterface<Request, Response> {
    protected final JpaRepository<Model, Long> repository;

    protected BaseQRCodeService(JpaRepository<Model, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Response save(Request request) {
        Model entity = this.toEntity(request);
        return this.toResponse(this.repository.save(entity));
    }

    @Override
    public List<Response> qrCodeList() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Response findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new QRCodeNotFoundException("Registro nao encontrado id: " + id));
    }

    protected abstract Model toEntity(Request request);

    protected abstract Response toResponse(Model entity);
}
package com.dock.qrcode.application.usecase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQRCodeUseCase<Request, Response, Model> implements QRCodeUseCase<Request, Response> {
    protected final JpaRepository<Model, Long> repository;

    protected AbstractQRCodeUseCase(JpaRepository<Model, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Response save(Request request) {
        Model entity = this.toEntity(request);
        return this.toResponse(this.repository.save(entity));
    }

    @Override
    public List<Response> qrCodeList() {
        List<Model> entities = new ArrayList<>();
        repository.findAll().forEach(entities::add);
        return entities.stream().map(this::toResponse).toList();
    }

    @Override
    public Response findById(Long id) throws Exception {
        Model entity = repository.findById(id).orElseThrow(() -> new Exception("Registro nao encontrado"));
        return toResponse(entity);
    }

    protected abstract Model toEntity(Request request);

    protected abstract Response toResponse(Model entity);
}

package com.dock.qrcode.application.usecase;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractQRCodeUseCase<T, R, E> implements QRCodeUseCase<T, R> {
    protected final JpaRepository<E, Long> repository;

    protected AbstractQRCodeUseCase(JpaRepository<E, Long> repository) {
        this.repository = repository;
    }

    @Override
    public void save(T request) {
        E entity = this.toEntity(request);
        this.repository.save(entity);
    }

    @Override
    public List<R> qrCodeList() {
        List<E> entities = new ArrayList<>();
        repository.findAll().forEach(entities::add);
        return entities.stream().map(this::toResponse).toList();
    }

    @Override
    public R findById(Long id) throws Exception {
        E entity = repository.findById(id).orElseThrow(() -> new Exception("Registro nao encontrado"));
        return toResponse(entity);
    }

    protected abstract E toEntity(T request);

    protected abstract R toResponse(E entity);
}

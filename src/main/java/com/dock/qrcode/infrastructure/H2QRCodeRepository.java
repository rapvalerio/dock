package com.dock.qrcode.infrastructure;

import com.dock.qrcode.domain.model.QRCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2QRCodeRepository extends CrudRepository<QRCode, Long> {
}

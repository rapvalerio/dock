package com.dock.qrcode.infrastructure;

import com.dock.qrcode.domain.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2QRCodeRepository extends JpaRepository<QRCode, Long> {
}

package com.dock.qrcode.infrastructure;

import com.dock.qrcode.domain.model.QRCodeDueDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2QRCodeDueDateRepository extends JpaRepository<QRCodeDueDate, Long> {
}

package com.dock.qrcode.infrastructure;

import com.dock.qrcode.domain.model.QRCodeDueDate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface H2QRCodeDueDateRepository extends CrudRepository<QRCodeDueDate, Long> {
}

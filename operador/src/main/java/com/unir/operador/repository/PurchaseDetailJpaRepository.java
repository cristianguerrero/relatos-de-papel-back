package com.unir.operador.repository;

import com.unir.operador.model.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailJpaRepository extends JpaRepository<PurchaseDetail, Long> {
}

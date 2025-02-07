package com.unir.operador.repository;

import com.unir.operador.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseJpaRepository extends JpaRepository<Purchase, Long> {
}

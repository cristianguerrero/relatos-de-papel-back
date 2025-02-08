package com.unir.operador.service;

import com.unir.operador.dto.PurchaseRequest;
import com.unir.operador.model.Purchase;

import java.util.List;

public interface PurchaseService {
    Purchase crearCompraConDetalles(PurchaseRequest request);
    List<Purchase> obtenerTodas();
}

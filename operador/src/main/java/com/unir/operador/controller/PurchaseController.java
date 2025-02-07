package com.unir.operador.controller;

import com.unir.operador.dto.PurchaseRequest;
import com.unir.operador.model.Purchase;
import com.unir.operador.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Registrar una compra con sus detalles
    @PostMapping
    public ResponseEntity<Purchase> crearCompra(@RequestBody PurchaseRequest request) {
        Purchase purchase = purchaseService.crearCompraConDetalles(request);
        return ResponseEntity.ok(purchase);
    }

    // Obtener todas las compras
    @GetMapping
    public ResponseEntity<List<Purchase>> listarCompras() {
        return ResponseEntity.ok(purchaseService.obtenerTodas());
    }
}


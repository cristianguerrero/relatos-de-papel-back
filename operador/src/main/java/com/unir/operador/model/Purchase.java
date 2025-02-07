package com.unir.operador.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private LocalDateTime purchaseDate;
    private Double total;

    // Relación One-to-Many con PurchaseDetail
    // "mappedBy" = el nombre del atributo en PurchaseDetail que referencia a Purchase
    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseDetail> details = new ArrayList<>();

    public Purchase() {
    }

    public Purchase(String userId, LocalDateTime purchaseDate, Double total) {
        this.userId = userId;
        this.purchaseDate = purchaseDate;
        this.total = total;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Double getTotal() {
        return total;
    }

    public List<PurchaseDetail> getDetails() {
        return details;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setDetails(List<PurchaseDetail> details) {
        this.details = details;
    }

    // Metodo helper para agregar detalles (opcional)
    public void addDetail(PurchaseDetail detail) {
        this.details.add(detail);
        detail.setPurchase(this);
    }

    // Metodo helper para quitar detalles (opcional)
    public void removeDetail(PurchaseDetail detail) {
        this.details.remove(detail);
        detail.setPurchase(null);
    }
}

package com.unir.operador.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "purchase_details")
public class PurchaseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer itemId;
    private Integer quantity;
    private Double pricePerUnit;
    private Double lineTotal;

    // Relación Many-to-One con Purchase
    @ManyToOne
    @JoinColumn(name = "purchase_id") // Nombre de la columna FK
    @JsonIgnore
    private Purchase purchase;

    public PurchaseDetail() {
    }

    public PurchaseDetail(Integer itemId, Integer quantity, Double pricePerUnit, Double lineTotal) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.lineTotal = lineTotal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public Double getLineTotal() {
        return lineTotal;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setLineTotal(Double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}


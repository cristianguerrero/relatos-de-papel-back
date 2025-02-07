package com.unir.operador.dto;

import java.util.List;

public class PurchaseRequest {

    private String userId;
    private List<PurchaseItemDTO> items;  // Lista de ítems a comprar

    public PurchaseRequest() {
    }

    public PurchaseRequest(String userId, List<PurchaseItemDTO> items) {
        this.userId = userId;
        this.items = items;
    }

    public String getUserId() {
        return userId;
    }

    public List<PurchaseItemDTO> getItems() {
        return items;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setItems(List<PurchaseItemDTO> items) {
        this.items = items;
    }
}

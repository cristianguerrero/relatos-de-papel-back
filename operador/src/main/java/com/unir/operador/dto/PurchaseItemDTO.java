package com.unir.operador.dto;

public class PurchaseItemDTO {

    private Integer itemId;
    private Integer quantity;

    public PurchaseItemDTO() {
    }

    public PurchaseItemDTO(Integer itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}


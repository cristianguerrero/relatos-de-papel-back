package com.unir.operador.dto;

public class ItemResponseDTO {

    private Integer id;
    private Double price;
    private Integer stock;
    private boolean visible;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Integer id, Double price, Integer stock, boolean visible) {
        this.id = id;
        this.price = price;
        this.stock = stock;
        this.visible = visible;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}


package com.puntawat.metsofteng2.week2.model;

import javafx.beans.property.SimpleStringProperty;

public class PurchaseItem {
    private String name;
    private double price;
    private int quantity;

    public PurchaseItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }
}

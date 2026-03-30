package com.puntawat.metsofteng2.week2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static ShoppingCart instance = null;

    private final ObservableList<PurchaseItem> purchaseItems;

    private ShoppingCart() {
        this.purchaseItems = FXCollections.observableArrayList();
    }

    public void addItem(PurchaseItem purchaseItem) {
        purchaseItems.add(purchaseItem);
    }

    public void removeItem(PurchaseItem purchaseItem) {
        purchaseItems.remove(purchaseItem);
    }

    public ObservableList<PurchaseItem> getPurchaseItems() {
        return purchaseItems;
    }

    public double getTotalCost() {
        double total = 0;
        for (var items : purchaseItems) {
            total += items.getTotalCost();
        }
        return total;
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }
}

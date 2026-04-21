package com.puntawat.metsofteng2.week2.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShoppingCart {

    private static ShoppingCart instance = null;
    private CartRecord cartRecord;
    private CartService cartService;

    private final ObservableList<PurchaseItem> purchaseItems;

    private ShoppingCart() {
        this.purchaseItems = FXCollections.observableArrayList();
        cartRecord = new CartRecord();
        cartService = new CartService();
        cartRecord.setCreatedAt(new Date());
        cartRecord.setTotalItems(0);
    }

    public void addItem(PurchaseItem purchaseItem) {
        purchaseItems.add(purchaseItem);
        CartItem cartItem = new CartItem(cartRecord, cartRecord.getTotalItems()+1, purchaseItem.getPrice(), purchaseItem.getQuantity(), purchaseItem.getTotalCost());
        cartRecord.addItem(cartItem);
        cartRecord.setTotalCost(getTotalCost());
        cartRecord.setTotalItems(cartRecord.getTotalItems() + 1);
        cartService.saveCart(cartRecord);
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

    public CartRecord getCartRecord() {
        return cartRecord;
    }

    public CartService getCartService() {
        return cartService;
    }
}

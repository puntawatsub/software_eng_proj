package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_record_id")
    private CartRecord cartRecord;

    @Column(name = "item_number")
    private Integer itemNumber;

    private Double price;
    private Integer quantity;
    private Double subtotal;

    public CartRecord getCartRecord() {
        return cartRecord;
    }

    public CartItem(CartRecord cartRecord, Integer itemNumber, Double price, Integer quantity, Double subtotal) {
        this.cartRecord = cartRecord;
        this.itemNumber = itemNumber;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public CartItem() {
    }

    public void setCartRecord(CartRecord cartRecord) {
        this.cartRecord = cartRecord;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Integer getId() {
        return id;
    }
}
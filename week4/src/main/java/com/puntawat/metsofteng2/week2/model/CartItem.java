package com.puntawat.metsofteng2.week2.model;

import com.puntawat.metsofteng2.week2.jacoco.ExcludeFromJacocoGeneratedReport;
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

    @ExcludeFromJacocoGeneratedReport
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

    @ExcludeFromJacocoGeneratedReport
    public void setCartRecord(CartRecord cartRecord) {
        this.cartRecord = cartRecord;
    }
    @ExcludeFromJacocoGeneratedReport
    public Integer getItemNumber() {
        return itemNumber;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    @ExcludeFromJacocoGeneratedReport
    public Double getPrice() {
        return price;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setPrice(Double price) {
        this.price = price;
    }

    @ExcludeFromJacocoGeneratedReport
    public Integer getQuantity() {
        return quantity;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @ExcludeFromJacocoGeneratedReport
    public Double getSubtotal() {
        return subtotal;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    @ExcludeFromJacocoGeneratedReport
    public Integer getId() {
        return id;
    }
}
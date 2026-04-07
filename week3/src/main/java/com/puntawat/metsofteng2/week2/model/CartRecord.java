package com.puntawat.metsofteng2.week2.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Table(name = "cart_records")
public class CartRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_items", nullable = false)
    private Integer totalItems;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    private String language;

    @Column(name = "created_at", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // cascade = ALL means if we save the Cart, Hibernate saves the items too.
    // orphanRemoval = true means if an item is removed from this list, it's deleted from DB.
    @OneToMany(mappedBy = "cartRecord", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    // Helper method to keep both sides of the relationship in sync
    public void addItem(CartItem item) {
        items.add(item);
        item.setCartRecord(this);
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }
}

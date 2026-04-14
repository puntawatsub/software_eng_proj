package com.puntawat.metsofteng2.week2.model;
import com.puntawat.metsofteng2.week2.jacoco.ExcludeFromJacocoGeneratedReport;
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

    @ExcludeFromJacocoGeneratedReport
    // Helper method to keep both sides of the relationship in sync
    public void addItem(CartItem item) {
        items.add(item);
        item.setCartRecord(this);
    }

    @ExcludeFromJacocoGeneratedReport
    public Integer getTotalItems() {
        return totalItems;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    @ExcludeFromJacocoGeneratedReport
    public Double getTotalCost() {
        return totalCost;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    @ExcludeFromJacocoGeneratedReport
    public String getLanguage() {
        return language;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setLanguage(String language) {
        this.language = language;
    }

    @ExcludeFromJacocoGeneratedReport
    public Date getCreatedAt() {
        return createdAt;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @ExcludeFromJacocoGeneratedReport
    public List<CartItem> getItems() {
        return items;
    }

    @ExcludeFromJacocoGeneratedReport
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    @ExcludeFromJacocoGeneratedReport
    public Integer getId() {
        return id;
    }
}

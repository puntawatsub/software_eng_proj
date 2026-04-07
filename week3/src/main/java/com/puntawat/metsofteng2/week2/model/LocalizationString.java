package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.*;

@Entity
@Table(name = "localization_strings")
public class LocalizationString {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`key`", nullable = false)
    private String key;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false, length = 10)
    private String language;

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

package com.puntawat.metsofteng2.week2.service;

public enum Language {
    en_GB("en-GB", "English (UK)"),
    fi_FI("fi-FI", "suomi (Suomi)"),
    sv_FI("sv-FI", "svenska (Finland)"),
    ja_JP("ja-JP", "日本語 (日本)"),
    ar_AE("ar-AE", "العربية (الإمارات)");

    private final String lang;
    private final String display;

    Language(String lang, String display) {
        this.lang = lang;
        this.display = display;
    }

    public String getLang() {
        return lang;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return display;
    }
}

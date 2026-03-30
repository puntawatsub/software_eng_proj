package com.puntawat.metsofteng2.week2.model;

import java.util.Locale;
import java.util.prefs.Preferences;

public class SelectedLocale {
    private static SelectedLocale instance;
    private String selected;

    private SelectedLocale() {
        selected = null;
    }

    public static SelectedLocale getInstance() {
        if (instance == null) {
            instance = new SelectedLocale();
        }
        return instance;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}

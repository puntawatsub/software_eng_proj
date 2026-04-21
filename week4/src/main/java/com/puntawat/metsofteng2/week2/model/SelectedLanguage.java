package com.puntawat.metsofteng2.week2.model;

import com.puntawat.metsofteng2.week2.service.Language;

public class SelectedLanguage {
    private static SelectedLanguage instance;
    private Language selected;

    private SelectedLanguage() {
        selected = null;
    }

    public static SelectedLanguage getInstance() {
        if (instance == null) {
            instance = new SelectedLanguage();
        }
        return instance;
    }

    public Language getSelected() {
        return selected;
    }

    public void setSelected(Language selected) {
        this.selected = selected;
    }
}

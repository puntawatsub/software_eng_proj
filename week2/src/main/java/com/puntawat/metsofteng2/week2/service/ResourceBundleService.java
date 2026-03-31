package com.puntawat.metsofteng2.week2.service;

import java.text.Bidi;
import java.util.ResourceBundle;

public class ResourceBundleService {
    private static ResourceBundleService instance = null;
    private ResourceBundle rb;

    private ResourceBundleService() {}

    public static ResourceBundleService getInstance() {
        if (instance == null) {
            instance = new ResourceBundleService();
        }
        return instance;
    }

    public void setResourceBundle(ResourceBundle rb) {
        this.rb = rb;
    }

    public ResourceBundle getResourceBundle() {
        return rb;
    }

    public boolean isRTL() {
        String refString = rb.getString("ref");
        Bidi bidi = new Bidi(refString, Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT);
        return bidi.isRightToLeft();
    }
}

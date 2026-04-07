package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.EntityManager;
import java.util.*;

import jakarta.persistence.EntityManager;
import java.util.*;

public class DbResourceBundle extends ResourceBundle {
    private final Map<String, Object> lookup = new HashMap<>();
    private final Locale locale;

    public DbResourceBundle(Locale locale) {
        this.locale = locale;
        loadFromDatabase();
    }

    private void loadFromDatabase() {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        try {
            // Fetch all keys for the current language code (e.g., 'ar', 'fi', 'en')
            List<LocalizationString> strings = em.createQuery(
                            "SELECT l FROM LocalizationString l WHERE l.language = :lang",
                            LocalizationString.class)
                    .setParameter("lang", locale.getLanguage())
                    .getResultList();

            for (LocalizationString ls : strings) {
                lookup.put(ls.getKey(), ls.getValue());
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected Object handleGetObject(String key) {
        return lookup.get(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return Collections.enumeration(lookup.keySet());
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}

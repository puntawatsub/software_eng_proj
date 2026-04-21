package com.puntawat.metsofteng2.week2.service;

import com.puntawat.metsofteng2.week2.model.DbResourceBundle;
import com.puntawat.metsofteng2.week2.model.HibernateUtil;
import com.puntawat.metsofteng2.week2.model.LocalizationString;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ResourceBundleServiceTest {

    @BeforeEach
    void seedDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        HibernateUtil.setEntityManagerFactory(emf);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        LocalizationString s1 = new LocalizationString();
        s1.setKey("ref");
        s1.setValue("suomi");
        s1.setLanguage("fi");

        LocalizationString s2 = new LocalizationString();
        s2.setKey("ref");
        s2.setValue("العربية");
        s2.setLanguage("ar");

        em.persist(s1);
        em.persist(s2);

        em.getTransaction().commit();
        em.close();
    }

    @Test
    void isRTL() {
        ResourceBundleService.getInstance().setResourceBundle(new DbResourceBundle(Locale.forLanguageTag("ar-AE")));
        assertTrue(ResourceBundleService.getInstance().isRTL());
        ResourceBundleService.getInstance().setResourceBundle(new DbResourceBundle(Locale.forLanguageTag("fi-FI")));
        assertFalse(ResourceBundleService.getInstance().isRTL());
    }
}
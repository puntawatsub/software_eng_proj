package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class DbResourceBundleTest {

    @BeforeEach
    void seedDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
        HibernateUtil.setEntityManagerFactory(emf);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        LocalizationString s1 = new LocalizationString();
        s1.setKey("hello");
        s1.setValue("moi");
        s1.setLanguage("fi");

        LocalizationString s2 = new LocalizationString();
        s2.setKey("hello");
        s2.setValue("hej");
        s2.setLanguage("sv");

        LocalizationString s3 = new LocalizationString();
        s3.setKey("bye");
        s3.setValue("moi moi");
        s3.setLanguage("fi");

        LocalizationString s4 = new LocalizationString();
        s4.setKey("bye");
        s4.setValue("hejdå");
        s4.setLanguage("sv");

        em.persist(s1);
        em.persist(s2);
        em.persist(s3);
        em.persist(s4);

        em.getTransaction().commit();
        em.close();
    }

    @ParameterizedTest
    @CsvSource({
            "fi-FI, hello, moi",
            "sv-FI, hello, hej",
            "fi-FI, bye, moi moi",
            "sv-FI, bye, hejdå",
            "fi-FI, banana, null"
    })
    void handleGetObject(String local, String key, String value) {
        assertEquals(new DbResourceBundle(Locale.forLanguageTag(local)).handleGetObject(key), value.equals("null") ? null : value);
    }

    @Test
    void getKeys() {
        DbResourceBundle bundle = new DbResourceBundle(Locale.forLanguageTag("fi-FI"));
        assertEquals(List.of("hello", "bye"), Collections.list(bundle.getKeys()));
    }

    @Test
    void getLocale() {
        DbResourceBundle finnishBundle = new DbResourceBundle(Locale.forLanguageTag("fi-FI"));
        DbResourceBundle swedishBundle = new DbResourceBundle(Locale.forLanguageTag("sv-FI"));

        assertEquals(finnishBundle.getLocale(),Locale.forLanguageTag("fi-FI"));
        assertEquals(swedishBundle.getLocale(),Locale.forLanguageTag("sv-FI"));
    }
}
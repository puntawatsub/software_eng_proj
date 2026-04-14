package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {
    // Note: We use EntityManagerFactory for JPA (persistence.xml)
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("production");
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public static void setEntityManagerFactory(EntityManagerFactory testEmf) {
        entityManagerFactory = testEmf;
    }
}
package com.puntawat.metsofteng2.week2.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CartService {

    public void saveCart(CartRecord cart) {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();


            em.persist(cart);


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        }
    }
}
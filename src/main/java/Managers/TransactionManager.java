package Managers;

import models.Transaction;

import javax.persistence.EntityManager;

public class TransactionManager extends BaseManager{


    public static void saveTransaction(Transaction Transaction) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(Transaction);
        em.getTransaction().commit();
    }
}

package managers;

import models.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TransactionManager extends BaseManager{


    public static void saveTransaction(Transaction Transaction) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(Transaction);
        em.getTransaction().commit();
    }

    public static List<Transaction> loadTransactionsOutFromId(String accountId){
        EntityManager em = getEntityManager();
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t WHERE t.source='"+accountId+"'", Transaction.class);
        List<Transaction> fromBase = query.getResultList();
        return fromBase;
    }

    public static List<Transaction> loadTransactionsInFromId(String accountId){
        EntityManager em = getEntityManager();
        TypedQuery<Transaction> query = em.createQuery("SELECT t FROM Transaction t WHERE t.destination='"+accountId+"'", Transaction.class);
        List<Transaction> fromBase = query.getResultList();
        return fromBase;
    }
}

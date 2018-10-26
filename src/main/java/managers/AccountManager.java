package managers;

import models.Account;

import javax.persistence.EntityManager;

public class AccountManager extends BaseManager{


    public static void saveAccount(Account Account) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(Account);
        em.getTransaction().commit();
    }

    public static Account loadAccountFromId(String id) {
        EntityManager em = getEntityManager();
        Account fromBase = em.find(Account.class, id);
        return fromBase;
    }
}

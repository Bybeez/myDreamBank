package managers;

import models.AccountType;

import javax.persistence.EntityManager;

public class AccountTypeManager extends BaseManager{

    public static void saveAccountType(AccountType accountType) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(accountType);
        em.getTransaction().commit();
    }
}

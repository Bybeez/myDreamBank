package Managers;

import models.Account;
import models.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AccountTypeManager extends BaseManager{

    public static void saveAccountType(AccountType accountType) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(accountType);
        em.getTransaction().commit();
    }
}

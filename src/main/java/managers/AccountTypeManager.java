package managers;

import models.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AccountTypeManager extends BaseManager{

    public static void saveAccountType(AccountType accountType) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(accountType);
        em.getTransaction().commit();
    }

    public static AccountType getAccountTypeFromId(String id) {
        EntityManager em = getEntityManager();
        TypedQuery<AccountType> query = em.createQuery("SELECT at FROM AccountType at WHERE at.id="+id+"", AccountType.class);
        AccountType fromBase = query.getResultList().stream().findFirst().orElse(null);
        return fromBase;
    }

    public static List<AccountType> getAccountTypes() {
        EntityManager em = getEntityManager();
        TypedQuery<AccountType> query = em.createQuery("SELECT at FROM AccountType at", AccountType.class);
        List<AccountType> accountTypes = query.getResultList();
        return accountTypes;
    }
}

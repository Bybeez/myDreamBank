package Managers;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class UserManager extends BaseManager{

    public static void saveUser(User user){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public static User loadUserByLogin(String login){
        EntityManager em = getEntityManager();
        User fromBase = em.find(User.class, login);
        return fromBase;
    }

    public static User loadUserByLoginAndPassword(String login, String password){
        EntityManager em = getEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login='"+login+"' and u.password='"+password+"'", User.class);
        User fromBase = query.getSingleResult();
        return fromBase;
    }

    public static void purgeTable(){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Transaction").executeUpdate();
        em.createQuery("DELETE FROM Account").executeUpdate();
        em.createQuery("DELETE FROM AccountType").executeUpdate();
        em.createQuery("DELETE FROM User").executeUpdate();
        em.getTransaction().commit();
    }

}

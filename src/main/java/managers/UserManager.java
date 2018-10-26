package managers;

import models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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
        User fromBase = query.getResultList().stream().findFirst().orElse(null);
        return fromBase;
    }

    public static List<User> loadAllUser() {
        EntityManager em = getEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();
        return users;
    }

    public static void updatePassword(String login, String newPassword) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE User u SET u.password = '"+ newPassword + "' WHERE u.login ='"+ login +"'");
        query.executeUpdate();
        em.getTransaction().commit();
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

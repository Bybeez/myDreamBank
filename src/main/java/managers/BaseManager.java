package managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BaseManager {
    private static final String UNIT_NAME = "myDreamBank";
    private static EntityManagerFactory factory;

    protected static EntityManagerFactory getInstanceManagerFactory() {
        if(factory == null){
            factory = Persistence.createEntityManagerFactory(UNIT_NAME);
        }
        return factory;
    }

    public static void shutdown() {
        if(factory != null){
            factory.close();
        }
    }

    public static EntityManager getEntityManager() {
        EntityManagerFactory factory = getInstanceManagerFactory();
        return factory.createEntityManager();
    }
}

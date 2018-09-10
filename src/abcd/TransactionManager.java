package abcd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TransactionManager {
	
	private static EntityManagerFactory emfactory ;
    private static EntityManager entitymanager;
	
    private static synchronized EntityManager initialize() {
    	emfactory = Persistence.createEntityManagerFactory( "abcd" );
        entitymanager = emfactory.createEntityManager( );
    	return entitymanager;
    }

    public static synchronized EntityManager getEntityManager(){
    	if(null != entitymanager) {
    		return entitymanager;
    	}
    	return initialize();
    }
    
    public static synchronized void shutdown(){
    	if (null != entitymanager) {
    		entitymanager.close();
    	}
     	if (null != emfactory) {
     		emfactory.close();
    	}
    }
}

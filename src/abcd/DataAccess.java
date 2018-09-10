package abcd;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import pojo.User;

public class DataAccess {
	
	public static UserX getUser(EntityManager e,  User u1) {
		String sqlString="SELECT u FROM UserX u where u.uuid = \"" + u1.getUuid()  + "\"";
		
		List<UserX>  l = e.createQuery(sqlString).getResultList();
		if(l.isEmpty()){
			return new UserX();
		}
		return l.get(0);
	}
	
	public static boolean insertUser(String id, String name, String category, String password) {
		
		/*
		try {
			
			EntityManager em = TransactionManager.getEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			UserX u =  getUser(em, id);
			
			u.setName(name);
			u.setPassword(password);
			u.setCategory(category);
		
			UserX uu = em.merge(u);
				
			et.commit();
			
			return uu.isModified();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} finally {
			TransactionManager.shutdown();
		}*/

		return false;
	}

	public static UserX  insertEntity(UserX ux) {
		try {
			
			EntityManager em = TransactionManager.getEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			UserX uu = em.merge(ux);
			et.commit();
			return ux;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		} finally {
			//TransactionManager.shutdown();
		}

		return null;
	}
	
	
	public static Address getAddressfromLazyList(List<Address> ads , pojo.Address pads) {
		
		if (ads != null)
		
		for(Address a: ads) {
			if(a.getUuid().equals(pads.getUuid()))
				return a;
		}
		
		return null;
	}
	
	
	public static List<Address>  getAddress(EntityManager em , UserX  ux , User u1) {
		List<Address> ads = ux.getAddress();
		
		for (pojo.Address pads: u1.getAddresses())
		{
			//System.out.println( pads.getUuid() );
			//String sqlString="SELECT a FROM Address a where a.uuid = \"" + pads.getUuid()  + "\"";
			//List<Address>  l = em.createQuery(sqlString).getResultList();
			
			Address a =  getAddressfromLazyList( ads , pads);
			if(null == a){
				a =  new Address();
				a.update(pads);
				ads.add(a);
			} else {
				a.update(pads);
			}

		}
		
		ux.setAddress(ads);
		return ads;
	
	}
	
	public static STATUS accessUpdateLogicAddress(EntityManager em ,Address  a) {
		
		if(!a.isActive()) {
			em.remove(a);
			System.out.println("Removing child and updating the parent..");
			return STATUS.Deleted;
		}
		
		if( a.isModified()) // Since there is no child
			return STATUS.Modified;
		return STATUS.Unchanged;
	}
	
	public static STATUS accessUpdateLogicUser(EntityManager em , UserX  ux) {
		
		STATUS s = STATUS.Unchanged;
		if(!ux.isActive()) {
			em.remove(ux);
			return STATUS.Deleted;
		}
		
		for (int i = ux.getAddress().size()-1; i>=0; i --)
		{
			Address a = ux.getAddress().get(i);
			STATUS status = accessUpdateLogicAddress(em,a);
			if(STATUS.Unchanged !=  status) {
				ux.setChangedOn(a.getChangedOn());
				ux.setChangedBy(a.getCreatedBy());
				if ( status == STATUS.Deleted){
					System.out.println("deleting");
					ux.getAddress().remove(a);
				}
				s = STATUS.Modified;
			}
		}
		
	
		
		
		if( ux.isModified()) {
			return STATUS.Modified;
		}
		return s;
	}

	public static void insert(User u1) {
			
		EntityManager em = TransactionManager.getEntityManager();
		UserX  ux = getUser(em, u1);
		ux.update(u1);
		List<Address> ads = getAddress(em, ux, u1);
		UserX updated =insertEntity(ux);

		if(updated != null ) {
			if ( STATUS.Unchanged != accessUpdateLogicUser(em, updated)) {
				System.out.println("Second trasaction...");
				try {
					EntityTransaction et = em.getTransaction();
					et.begin();
					em.merge(updated);
					et.commit();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				} finally {
				
				}
			}
		}
		
		TransactionManager.shutdown();
	}

}

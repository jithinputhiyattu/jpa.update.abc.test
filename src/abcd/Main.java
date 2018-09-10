package abcd;

import pojo.User;

public class Main {

	public static void main(String[] args) {
		
		//boolean result = DataAccess.insertUser("1", "Jithin", "dev", "#password#");
		//boolean address = DataAccess.insertAddress("1", "Jithin", "dev", "#password#");
		/*
		if (result) {
			System.out.println("Updated the table");
		} else {
			System.out.println("No need of table update");

		}*/
		
		
		User u1 = new User();
		
		u1.setCategory("admi");
		u1.setName("name c");
		u1.setPassword("passw");
		u1.setUuid("1fa77e3e-09a9-42b3-b575-67df285c5930");
		
		
		pojo.Address a1 = new pojo.Address();
		a1.setFlatname("Flat Name One c");
		a1.setPin(123);
		a1.setStreet("street");
		a1.setFlatnumber(1);
		a1.setUuid("9b1e9623-2920-4b61-85bf-cf5465f67659");
		
		pojo.Address a2 = new pojo.Address();
		a2.setFlatname("flat name 2");
		a2.setPin(12345);
		a2.setStreet("Street2");
		a2.setFlatnumber(107);
		a2.setUuid("0acca9d5-e9dd-4835-aeac-1cdbe30dced4");
		
		u1.addAddress(a1);
		u1.addAddress(a2);
		
		
		DataAccess.insert(u1);
		
		
	}

}

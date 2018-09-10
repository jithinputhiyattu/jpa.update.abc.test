package pojo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String category;
	private String name;
	private String password;
	private String uuid;
	
	private List<Address> addresses = new ArrayList<Address>();
	
	public User() {
		
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public void addAddress(Address a) {
		this.addresses.add(a);
	}

}

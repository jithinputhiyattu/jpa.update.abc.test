package abcd;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import pojo.User;


/**
 * The persistent class for the "userX" database table.
 * 
 */
@Entity
@Table(name="\"userX\"", schema="testpg")

@NamedQuery(name="UserX.findAll", query="SELECT u FROM UserX u")
public class UserX extends Base {
	private static final long serialVersionUID = 1L;

	private String category;
	private String name;
	private String password;
	
    @JoinColumn(name = "userid", referencedColumnName = "uuid")
	private List<Address> address =  new ArrayList<Address>();
    


	public UserX() {
	}
	public UserX(String name, String group, String password) {
		this.name = name;
		this.setCategory(group);
		this.password = password;
	}
	public UserX(String id) {
		this.setUuid(id);
	}
	public UserX(User u1) {
		this.update(u1);
	}
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public void update(User u1) {
		super.ubdating();
		this.name = u1.getName();
		this.password = u1.getPassword();
		this.category = u1.getCategory();

	}
	


}
package abcd;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity

@Table(name="\"address\"", schema="testpg")
@NamedQuery(name="Address.findAll", query="SELECT a FROM Address a")
public class Address extends Base {
	private static final long serialVersionUID = 1L;

 
	private String flatname;

	private Integer flatnumber;

	private Integer pin;

	private String street;

	private String userid;
	
    /*@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "uuid")
    UserX userx;
    
    public UserX getUserX(){
    	return userx;
    }

    public void setUserX(UserX userx) {
    	this.userx = userx;
    } */
    
    
 

	public Address() {
	}


	public String getFlatname() {
		return this.flatname;
	}

	public void setFlatname(String flatname) {
		this.flatname = flatname;
	}

	public Integer getFlatnumber() {
		return this.flatnumber;
	}

	public void setFlatnumber(Integer flatnumber) {
		this.flatnumber = flatnumber;
	}

	public Integer getPin() {
		return this.pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public void update(pojo.Address pads) {
		super.ubdating();
		this.flatname = pads.getFlatname();
		this.pin= pads.getPin();
		this.flatnumber = pads.getFlatnumber();
		this.street = pads.getStreet();

	}


}
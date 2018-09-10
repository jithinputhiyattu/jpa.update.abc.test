package pojo;

public class Address {

	private String flatname;

	private Integer flatnumber;

	private Integer pin;

	private String street;

	private String userid;
	
	private String uuid;

	public String getFlatname() {
		return flatname;
	}

	public void setFlatname(String flatname) {
		this.flatname = flatname;
	}

	public Integer getFlatnumber() {
		return flatnumber;
	}

	public void setFlatnumber(Integer flatnumber) {
		this.flatnumber = flatnumber;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}

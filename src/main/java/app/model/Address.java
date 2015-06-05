package app.model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder={"type","zipcode","province","city","street"})
public class Address {
	private String province;
	private String city;
	private String street;
	private String zipcode;
	private String type;
	
	public Address() {
		super();
	}

	public Address(String province, String city) {
		super();
		this.province = province;
		this.city = city;
	}
	
	public Address(String province, String city, String street, String type) {
		super();
		this.province = province;
		this.city = city;
		this.street = street;
		this.type = type;
	}
	
	

	public Address(String province, String city, String street, String zipcode,
			String type) {
		super();
		this.province = province;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.type = type;
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Address [province=" + province + ", city=" + city + "]";
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}

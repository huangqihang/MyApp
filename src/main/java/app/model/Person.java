package app.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

	@XmlAttribute(name="id")
	private long id;
	private String name;
	private int age;
	private Date birth;
	
	@XmlElementWrapper(name="addressList") 
	@XmlElement(name="address")
	private List<Address> address = new ArrayList<Address>();
	
	@XmlTransient
	private long random; //此字段不会被序列化
	
	public Person(int id, String name, int age, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
	}
	
	public Person(long id, String name, int age, Date birth, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
		this.address.add(address);
	}



	public Person() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	


	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age
				+ ", birth=" + new SimpleDateFormat("yyyy-MM-dd").format(birth) + ", address=" + address + "]";
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
	
	public Person addAddress(Address addr) {
		this.address.add(addr);
		return this;
	}

}

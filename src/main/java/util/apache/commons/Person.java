package util.apache.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
	private long id;
	private String name;
	private int age;
	private Date birth;
	
	public Person(int id, String name, int age, Date birth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.birth = birth;
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
				+ ", birth=" + new SimpleDateFormat("yyyy-MM-dd").format(birth) + "]";
	}
	
	
}

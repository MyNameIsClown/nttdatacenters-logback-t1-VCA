package nttdatacenters_logback_t1_VCA;

import java.io.Serializable;

import com.github.javafaker.Faker;

@SuppressWarnings("serial")
public class User implements Serializable{
	private Faker faker;
	private int id;
	private String name;
	private String apellidos;
	private String address;
	private int phoneNumber;
	public User(int id) {
		super();
		this.setFaker(new Faker());
		this.id = id;
		this.name = faker.name().firstName();
		this.address = faker.address().streetAddress();
		this.apellidos = faker.name().lastName();
		this.phoneNumber = generarNumeroTelefono();
	}
	public User(int id, String name, String apellidos, String address, int phoneNumber) {
		super();
		this.setFaker(new Faker());
		this.id = id;
		this.name = name;
		this.apellidos = apellidos;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", apellidos=" + apellidos + ", address=" + address
				+ ", phoneNumber=" + phoneNumber;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public static Integer generarNumeroTelefono() {
		int counter = 1;
		Integer numeroTelefono [] = new Integer[9];
		String numeroTelefonoString [] = new String[9];
		numeroTelefono[0] = 6;
		numeroTelefonoString[0] = numeroTelefono[0].toString();
		while(numeroTelefono[8]==null) {
			numeroTelefono[counter] = (int) (Math.random()*10);
			numeroTelefonoString[counter] = numeroTelefono[counter].toString();
			counter++;
		}
		return Integer.parseInt(String.join("",numeroTelefonoString));
	}
	public Faker getFaker() {
		return faker;
	}
	public void setFaker(Faker faker) {
		this.faker = faker;
	}
	
}

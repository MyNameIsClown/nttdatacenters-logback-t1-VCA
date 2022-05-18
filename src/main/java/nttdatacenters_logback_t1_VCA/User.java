package nttdatacenters_logback_t1_VCA;

import java.io.Serializable;

import com.github.javafaker.Faker;

/**
 * User
 * @author Victor Carrasco Artacho
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	private Faker faker;
	private int id;
	private String name;
	private String lastName;
	private String address;
	private int phoneNumber;
	
	/**
	 * Constructor simple con el que se asignan datos aleatorios
	 * @param id
	 */
	public User(int id) {
		super();
		this.setFaker(new Faker());
		this.id = id;
		this.name = faker.name().firstName();
		this.address = faker.address().streetAddress();
		this.lastName = faker.name().lastName();
		this.phoneNumber = generatePhoneNumber();
	}
	/**
	 * Constructor Completo
	 * @param id
	 * @param name
	 * @param apellidos
	 * @param address
	 * @param phoneNumber
	 */
	public User(int id, String name, String apellidos, String address, int phoneNumber) {
		super();
		this.setFaker(new Faker());
		this.id = id;
		this.name = name;
		this.lastName = apellidos;
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
		return "id=" + id + ", name=" + name + ", apellidos=" + lastName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber;
	}
	public String getApellidos() {
		return lastName;
	}
	public void setApellidos(String apellidos) {
		this.lastName = apellidos;
	}
	
	/**
	 * generatePhoneNumber
	 * 
	 * get random phone Number
	 * @return
	 */
	public static Integer generatePhoneNumber() {
		int counter = 1;
		Integer phoneNumber [] = new Integer[9];
		String phoneNumberString [] = new String[9];
		//el primer numero siempre sera el 6
		phoneNumber[0] = 6;
		phoneNumberString[0] = phoneNumber[0].toString();
		while(phoneNumber[8]==null) {
			phoneNumber[counter] = (int) (Math.random()*10);
			phoneNumberString[counter] = phoneNumber[counter].toString();
			counter++;
		}
		return Integer.parseInt(String.join("",phoneNumberString));
	}
	public Faker getFaker() {
		return faker;
	}
	public void setFaker(Faker faker) {
		this.faker = faker;
	}
}

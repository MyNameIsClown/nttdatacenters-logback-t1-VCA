package nttdatacenters_logback_t1_VCA;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private int id;
	private String name;
	private String apellidos;
	private String address;
	private int phoneNumber;
	public User(int id, String name, String apellidos) {
		super();
		this.id = id;
		this.name = name;
		this.setApellidos(apellidos);
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
		return "id=" + id + ", [name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber + "]";
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
}

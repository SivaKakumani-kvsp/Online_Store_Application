package common;

import java.io.Serializable;

public class User implements Serializable {

	/**
	 * 
	 */
	public User(String name, String email, String phone, String address, String password, Role UserRole) {

		this.Name = name;
		this.EmailID = email;
		this.Phone = phone;
		this.Address = address;
		this.Password = password;
		this.UserRole = UserRole;
	}

	private static final long serialVersionUID = 1L;
	String EmailID;

	public String getEmailID() {
		return EmailID;
	}

	public String getPassword() {
		return Password;
	}

	public String getName() {
		return Name;
	}

	public String getAddress() {
		return Address;
	}

	public String getPhone() {
		return Phone;
	}

	public Role getUserRole() {
		return UserRole;
	}

	String Password;
	String Name;
	String Address;
	String Phone;
	Role UserRole;
}

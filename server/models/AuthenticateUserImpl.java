package server.models;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import common.RemoteUser;
import common.User;

// User model class
public class AuthenticateUserImpl extends UnicastRemoteObject implements RemoteUser {

	private Map<String, User> UserMap;

	public AuthenticateUserImpl() throws RemoteException {
		super();
		UserMap = new HashMap<String, User>();
		// TODO Auto-generated constructor stub
	}

	// attributes of the class

	@Override
	public User login(String EmailID, String password) throws RemoteException {
		if (UserMap.containsKey(EmailID) == true) {
			User user = UserMap.get(EmailID);
			if (user.getPassword().equals(password) == true) {
				return user;
			} else {
				return null;
			}

		} else {
			return null;
		}

	}

	// method to register User
	@Override
	public String register(User user) throws RemoteException {

		if (UserMap.containsKey(user.getEmailID()) == true) {
			return "Email ID already Found Cant Register!";
		} else {
			UserMap.put(user.getEmailID(), user);
			System.out.println("new User registered");
			return "User Successfully Registered";

		}

	}

}

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Remote interface for Customer
public interface RemoteUser extends Remote {

	// These methods are implemented by CustomerImpl class(the model).

	public User login(String name, String password) throws RemoteException;

	public String register(User user) throws RemoteException;

}

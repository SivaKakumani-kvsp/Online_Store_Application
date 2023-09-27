package server;

import java.rmi.Naming;

import server.models.OnlineStoreImpl;
import server.models.AuthenticateUserImpl;

public class Server {
	public static void main(String args[]) {

		try {

			// creating an object
			AuthenticateUserImpl userRemoteObject = new AuthenticateUserImpl();

			// binding the object by remote user
			String remoteUserName = "//" + args[0] + ":" + args[1] + "/RemoteUser";

			Naming.rebind(remoteUserName, userRemoteObject);
			
			OnlineStoreImpl onlineStoreImpl = new OnlineStoreImpl();
			
			String remoteOnlineStore = "//" + args[0] + ":" + args[1] + "/RemoteOnlineStore";

			Naming.rebind(remoteOnlineStore, onlineStoreImpl);
			
			
			// after connection establishment
			System.out.println("Server is ready to communicate with client at " + "//" + args[0] + ":" + args[1]);
		} catch (Exception e) {

			System.out.println("Server error, please check!: " + e.getMessage());
			e.printStackTrace();

		}
	}

}

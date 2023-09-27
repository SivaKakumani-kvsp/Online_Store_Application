package client.commands;

import java.rmi.RemoteException;

import common.RemoteOnlineStore;

public class ExitStoreCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;

	public ExitStoreCommand(RemoteOnlineStore remoteProductStub, String userID) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
	}
	


	@Override
	public void execute() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			this.remoteProductStub.clearShoppingcart(this.userID);
			
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
			
		}
		
		
	}

}

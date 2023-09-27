package client.commands;

import java.rmi.RemoteException;

import common.RemoteOnlineStore;

public class ClearCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	public ClearCommand(RemoteOnlineStore remoteProductStub, String userID ) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
	}
	

	@Override
	public void execute() {
		try {
		// TODO Auto-generated method stub
		this.remoteProductStub.clearProductMessage(this.userID);
		this.remoteProductStub.clearOnlineStore();
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + e.getMessage());
		}
	}

}

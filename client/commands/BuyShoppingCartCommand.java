package client.commands;

import java.rmi.RemoteException;

import common.RemoteOnlineStore;

public class BuyShoppingCartCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;

	public BuyShoppingCartCommand(RemoteOnlineStore remoteProductStub, String userID) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
	}

	@Override
	public void execute() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			this.remoteProductStub.clearProductMessage(this.userID);
			this.remoteProductStub.buyShoppingcart(this.userID);

			if (this.remoteProductStub.getProductMessage(this.userID) != null) {
				System.out.println(this.remoteProductStub.getProductMessage(this.userID));
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());

		}

	}

}

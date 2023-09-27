package client.commands;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.RemoteOnlineStore;

public class RemoveProductCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;
	
	public RemoveProductCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input ) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
		this.input = input;
	}
	

	@Override
	public void execute() {
		try {
		// TODO Auto-generated method stub
		this.remoteProductStub.clearProductMessage(this.userID);
		System.out.print("ProductID > ");
		String productID = this.input.next();
		this.remoteProductStub.removeProduct(productID);
		if(this.remoteProductStub.getProductMessage(this.userID) != null)
		{
			System.out.println(this.remoteProductStub.getProductMessage(this.userID));
		}
	
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + e.getMessage());
		}
	}

}

package client.commands;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.Product;
import common.RemoteOnlineStore;

public class ClearProductItemCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;
	
	public ClearProductItemCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input ) {
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
		String productID =  this.input.next();
		Product product = this.remoteProductStub.getProduct(productID);
		if(product==null)
		{
			System.out.println("The Product doesnt exist "+productID);
			return;
		}
			
		this.remoteProductStub.clearProductItem(productID);
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

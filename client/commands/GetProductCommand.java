package client.commands;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.Product;
import common.RemoteOnlineStore;

public class GetProductCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;
	public GetProductCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input ) {
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
		}
		else
		{
			System.out.println("ProductID > "+ product.getpId());
			System.out.println("Name > "+ product.getProduct().getpName());
			System.out.println("Description > "+ product.getProduct().getpType());
			System.out.println("Price > "+ product.getProduct().getpPrice().toString());
			System.out.println("Quantity > "+ product.getQuantity());
		}
	
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + e.getMessage());
		}
	}

}

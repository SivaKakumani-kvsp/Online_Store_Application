package client.commands;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.Product;
import common.RemoteOnlineStore;

public class AddProductToShoppingCartCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;

	public AddProductToShoppingCartCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
		 this.input = input;
	}
	


	@Override
	public void execute() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			this.remoteProductStub.clearProductMessage(this.userID);
			System.out.print("ProductID > ");
		    String productID =  this.input.next();
		    System.out.print("Quantity > ");
		    Integer quantity =  this.input.nextInt();
		    Product product = this.remoteProductStub.getProduct(productID);
			if(product==null)
			{
				System.out.println("The Product doesnt exist "+productID);
				return;
			}
		    this.remoteProductStub.addProductToShoppingcart(this.userID, productID, quantity);
		    if(this.remoteProductStub.getProductMessage(this.userID) != null)
			{
				System.out.println(this.remoteProductStub.getProductMessage(this.userID));
			}
		}
		catch(Exception e) {
			System.out.println("Exception " + e.getMessage());
			
		}
		
		
	}

}

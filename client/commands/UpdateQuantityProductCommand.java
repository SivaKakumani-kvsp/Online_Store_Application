package client.commands;

import java.rmi.RemoteException;
import java.util.Scanner;

import common.Product;
import common.RemoteOnlineStore;

public class UpdateQuantityProductCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;
	public UpdateQuantityProductCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input) {
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
		System.out.print("To Increase Quanity + To Decrease Quantiy - To clear stock set 0 > ");
		Integer updateQuantity =  this.input.nextInt();
		if(updateQuantity == 0)
		{
			this.remoteProductStub.clearProductItem(productID);
		}
		else if(updateQuantity > 0)
		{
			this.remoteProductStub.increaseProductItemQuantity(productID, updateQuantity);
		}
		else if(updateQuantity < 0)
		{
			updateQuantity = Math.abs(updateQuantity);
			this.remoteProductStub.decreaseProductItemQuantity(productID, updateQuantity);
		}
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + e.getMessage());
		}
	}

}

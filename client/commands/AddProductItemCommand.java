package client.commands;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.ProductItem;
import common.RemoteOnlineStore;

public class AddProductItemCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;

	public AddProductItemCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
		this.input = input;
	}
	


	@Override
	public void execute() throws RemoteException {
		// TODO Auto-generated method stub
			this.remoteProductStub.clearProductMessage(this.userID);
			System.out.print("ProductID > ");
		    String productID = this.input.next();
		    System.out.print("Name > ");
		    String name =  this.input.next();
		    System.out.print("Description > ");
		    String description =  this.input.next();
		    System.out.print("Price > ");
		    BigDecimal price =  this.input.nextBigDecimal();
			this.remoteProductStub.addProductItem(productID, new ProductItem(name, description, price.setScale(2, RoundingMode.HALF_EVEN)));
			if(this.remoteProductStub.getProductMessage(this.userID) != null)
			{
				System.out.println(this.remoteProductStub.getProductMessage(this.userID));
			}
			this.remoteProductStub.clearProductMessage(this.userID);
			System.out.print("Add Quantity > ");
			Integer quantity =  this.input.nextInt();
			this.remoteProductStub.increaseProductItemQuantity(productID, quantity);
			if(this.remoteProductStub.getProductMessage(this.userID) != null)
			{
				System.out.println(this.remoteProductStub.getProductMessage(this.userID));
			}
		
	}

}

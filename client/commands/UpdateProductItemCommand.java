package client.commands;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.Scanner;

import common.Product;
import common.ProductItem;
import common.RemoteOnlineStore;

public class UpdateProductItemCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	private Scanner input;
	public UpdateProductItemCommand(RemoteOnlineStore remoteProductStub, String userID, Scanner input) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
		this.input = input;
	}

	@Override
	public void execute() {
		try {
			this.remoteProductStub.clearProductMessage(this.userID);
		// TODO Auto-generated method stub

		System.out.print("ProductID > ");
	    String productID =  this.input.next();
	    System.out.print("Name > ");
	    String name =  this.input.next();
	    System.out.print("Description > ");
	    String description =  this.input.next();
	    System.out.print("Price > ");
	    BigDecimal price =  this.input.nextBigDecimal();
		Product product = this.remoteProductStub.getProduct(productID);
		if(product == null)
		{
			System.out.println("The Product doesnt exist "+productID);
		}
		else
		{
			ProductItem productItem = product.getProduct();
			if(name !=null)
			{
				productItem.setpName(name);
			}
			if(description !=null)
			{
				productItem.setpType(description);
			}
			if(price !=null)
			{
				productItem.setpPrice(price);
			}
			
			this.remoteProductStub.updateProductItem(productID, productItem);
			if(this.remoteProductStub.getProductMessage(this.userID) != null)
			{
				System.out.println(this.remoteProductStub.getProductMessage(this.userID));
			}
				
		}
	
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception " + e.getMessage());
		}
	}

}

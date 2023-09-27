package client.commands;

import java.rmi.RemoteException;
import java.util.List;

import common.Product;
import common.RemoteOnlineStore;

public class GetProductListCommand implements Command {
	private RemoteOnlineStore remoteProductStub;
	private String userID;
	public GetProductListCommand(RemoteOnlineStore remoteProductStub, String userID ) {
		this.remoteProductStub = remoteProductStub;
		this.userID = userID;
	}
	

	@Override
	public void execute() {
		try {
			// TODO Auto-generated method stub
			this.remoteProductStub.clearProductMessage(this.userID);
			List<Product> products = this.remoteProductStub.getProductsList();
			for (Product product : products) {
				System.out.println("AVAILABLE--------------");
				System.out.println("ProductID > "+ product.getpId());
				System.out.println("Name > "+ product.getProduct().getpName());
				System.out.println("Description > "+ product.getProduct().getpType());
				System.out.println("Price > "+ product.getProduct().getpPrice().toString());
				System.out.println("Quantity > "+ product.getQuantity());
				System.out.println("-----------------------");
	        }
		
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				System.out.println("Exception " + e.getMessage());
			}
	}

}

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteOnlineStore extends Remote {

	// Getters for Member variables for Product class
	

	public List<Product> getProductsList() throws RemoteException;

	public Boolean addProductItem(String productID, ProductItem newProduct)
			throws RemoteException;
	
	public Boolean updateProductItem(String productID, ProductItem UpdateProduct)
			throws RemoteException;
	
	public Boolean clearProductItem(String productID)
			throws RemoteException;
	
	public Boolean removeProduct(String removalProductId)
			throws RemoteException;
	
	public Product getProduct(String productID)
			throws RemoteException;
	
	public Boolean clearOnlineStore()
			throws RemoteException;
	
	public Boolean increaseProductItemQuantity(String productID,  int QuantiyIncrease)
			throws RemoteException;
	
	public Boolean decreaseProductItemQuantity(String productID,  int QuantiyDecrease)
			throws RemoteException;
	
	public Boolean addProductToShoppingcart(String UserID, String productID, int quantity)
			throws RemoteException;
	
	public Boolean removeProductfromShoppingcart(String UserID, String productID)
			throws RemoteException;
	
	public Boolean clearShoppingcart(String UserID)
			throws RemoteException;
	
	public List<Product> showShoppingcart(String UserID)
			throws RemoteException;
	
	public Boolean buyShoppingcart(String UserID)
			throws RemoteException;
	
	public Boolean clearProductMessage(String UserID)
			throws RemoteException;
	
	public String getProductMessage(String UserID)
			throws RemoteException;


}

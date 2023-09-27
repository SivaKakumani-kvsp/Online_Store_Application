package server.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import common.Product;
import common.ProductItem;
import common.RemoteOnlineStore;

public class OnlineStoreImpl extends UnicastRemoteObject implements RemoteOnlineStore  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Product> productMap;
	private Map<String, Map<String, List<ProductItem>>> productShoppinCart;
	private Map<String, String> productMessage;
	
	public OnlineStoreImpl() throws RemoteException {
		super(); // calling constructor of unicastRemoteObject class
		productMap = new HashMap<String, Product>();
		productShoppinCart = new HashMap<String, Map<String, List<ProductItem>>>();
		productMessage = new HashMap<String, String>();
		this.initialize();
	}
	
	public void initialize() throws RemoteException
	{	
		
		Product p1 = new Product("Pens1", new ProductItem("Stationary", "Parker Black Ink", new BigDecimal(15.99).setScale(2, RoundingMode.HALF_EVEN)), 10);
		Product p2 = new Product("Toys1", new ProductItem("Gifts", "Teddy bear (Giant)", new BigDecimal(12.99).setScale(2, RoundingMode.HALF_EVEN)), 10);
		Product p3 = new Product("Gadgets1", new ProductItem("Electronics", "Apple Smart Watch\"", new BigDecimal(10.99).setScale(2, RoundingMode.HALF_EVEN)), 10);

		
	}
	

	@Override
	public List<Product> getProductsList() throws RemoteException {
	
		 return new ArrayList<Product>(this.productMap.values());
		
	}
	
	@Override
	public Boolean addProductItem(String productID, ProductItem newProduct) throws RemoteException {
		if(this.productMap.get(newProduct) != null)
		{
			return false;
		}
		else
		{
			this.productMap.put(productID, new Product(productID, newProduct, 0));
			return true;
		}
		
	}
	
	@Override
	public Boolean removeProduct(String removalProductId)
			throws RemoteException {
		if(this.productMap.get(removalProductId) == null)
		{
			return false;
		}
		else
		{
			this.productMap.remove(removalProductId);
			return true;
		}
		
	}

	@Override
	public Boolean updateProductItem(String productID, ProductItem UpdateProduct) throws RemoteException {
		// TODO Auto-generated method stub
		if(this.productMap.get(productID) == null)
		{
			return false;
		}
		else
		{
			Product product = this.productMap.get(productID);
			product.setProduct(UpdateProduct);
			this.productMap.put(productID, product);
			return true;
		}
	}

	@Override
	public Product getProduct(String productID) throws RemoteException {
		return this.productMap.get(productID);
	}

	@Override
	public Boolean clearOnlineStore() throws RemoteException {
		// TODO Auto-generated method stub
		this.productMap.clear();
		this.productShoppinCart.clear();
		this.initialize();
		return false;
		
	}

	@Override
	public Boolean clearProductItem(String productID) throws RemoteException {
		// TODO Auto-generated method stub
		this.productMap.get(productID).setQuantity(0);
		return true;
	}

	@Override
	public  Boolean increaseProductItemQuantity(String productID, int quantiyIncrease) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(this.productMap.get(productID).getQuantity()+quantiyIncrease);
		this.productMap.get(productID).setQuantity(this.productMap.get(productID).getQuantity()+quantiyIncrease);
		return true;
	}

	@Override
	public Boolean decreaseProductItemQuantity(String productID, int quantiyDecrease) throws RemoteException {
		// TODO Auto-generated method stub
		
		int productInShoppingCart = 0;
		for (Map.Entry<String,Map<String, List<ProductItem>>> entry : this.productShoppinCart.entrySet()) 
		{
			if(entry.getValue().get(productID) != null)
			{
				productInShoppingCart = productInShoppingCart + entry.getValue().get(productID).size();
			}
		}
	 
		int totalQuantity = this.productMap.get(productID).getQuantity() + productInShoppingCart;
				
		if(((this.productMap.get(productID).getQuantity() + productInShoppingCart) - quantiyDecrease) <0)
		{
			return false;
		}
		else
		{
			
			for (Map.Entry<String,Map<String, List<ProductItem>>> entry : this.productShoppinCart.entrySet()) 
			{	
				String delimiter = "--";
				List<String> itemCleared = new ArrayList<String>();
				for (Map.Entry<String, List<ProductItem>> product : entry.getValue().entrySet()) 
				{
					if(product.getKey().equals(productID))
					{
						itemCleared.add(productID);
					}
				}
				if(itemCleared.size()!=0)
				{
					this.productMessage.put(entry.getKey(), "Clearing Stock "+String.join(delimiter, itemCleared));
				}
				
				if(entry.getValue().get(productID) != null)
				{
					entry.getValue().remove(productID);
					
				}
			}
			
			this.productMap.get(productID).setQuantity(totalQuantity -quantiyDecrease);
			return true;
			
			
		}
		
	}
	
	
	@Override
	public Boolean addProductToShoppingcart(String UserID, String productID, int quantity) throws RemoteException {
		// TODO Auto-generated method stub
		if(this.productMap.get(productID).getQuantity() - quantity >=0)
		{
			int givenQuantity = this.productMap.get(productID).getQuantity();
			
			if(this.productShoppinCart.get(UserID) == null)
			{
				
				this.productShoppinCart.put(UserID, new HashMap<String, List<ProductItem>>());
			}
			
			for(int i =0; i<quantity; i++)
			{
				if(this.productShoppinCart.get(UserID).get(productID) == null)
				{
					this.productShoppinCart.get(UserID).put(productID, new ArrayList<ProductItem>());
					this.productShoppinCart.get(UserID).get(productID).add(this.productMap.get(productID).getProduct());
				}
				else
				{
					this.productShoppinCart.get(UserID).get(productID).add(this.productMap.get(productID).getProduct());
				}
				
			}
			this.productMap.get(productID).setQuantity(givenQuantity - quantity);
			return true;
		}
		else
		{
			this.productMessage.put(UserID, "This "+ productID + "have a quantity "+this.productMap.get(productID).getQuantity()+" "+" but given "+quantity);
			return false;
		}
		
	}
	@Override
	public Boolean removeProductfromShoppingcart(String UserID, String productID) throws RemoteException {
		// TODO Auto-generated method stub
		if(this.productShoppinCart.get(UserID) == null)
		{
			this.productMessage.put(UserID, UserID +" doesnt contain any products in Shopping cart");
			return false;
		}
		else
		{
			if(this.productShoppinCart.get(UserID).get(productID) == null)
			{
				this.productMessage.put(UserID, productID +" is not in Shopping cart");
				return false;
			}
			else
			{	
				Integer cartQuantity = this.productShoppinCart.get(UserID).get(productID).size();
				this.productMap.get(productID).setQuantity(this.productMap.get(productID).getQuantity()+cartQuantity);
				this.productShoppinCart.get(UserID).remove(productID);
				
				return true;
			}
			
		}
		
		
	}

	@Override
	public Boolean clearShoppingcart(String UserID) throws RemoteException {
		// TODO Auto-generated method stub
		
		if(this.productShoppinCart.get(UserID) == null)
		{
			this.productMessage.put(UserID, UserID +" doesnt contain any products in Shopping cart");
			return false;
		}
		else
		{
			for (Map.Entry<String,Map<String, List<ProductItem>>> entry : this.productShoppinCart.entrySet()) 
			{
				if(entry.getKey().equals(UserID))
				{
					for (Entry<String, List<ProductItem>> productMapLocal : entry.getValue().entrySet()) 
					{
						
						this.productMap.get(productMapLocal.getKey()).setQuantity(this.productMap.get(productMapLocal.getKey()).getQuantity()+productMapLocal.getValue().size());
					}
				
				}
			}
			this.productShoppinCart.get(UserID).clear();
			return true;
		}
	}

	@Override
	public Boolean buyShoppingcart(String UserID) throws RemoteException {
		// TODO Auto-generated method stub
		List<String> productShoppingNames = new ArrayList<String>();
		for (Map.Entry<String,Map<String, List<ProductItem>>> entry : this.productShoppinCart.entrySet()) 
		{
			if(entry.getKey().equals(UserID))
			{
				for (Entry<String, List<ProductItem>> productMapLocal : entry.getValue().entrySet()) 
				{
					productShoppingNames.add(productMapLocal.getKey() + " Quanity "+ productMapLocal.getValue().size());
				}
				if(productShoppingNames.size()!=0)
				{
					String result = String.join(",\n", productShoppingNames);
					this.productMessage.put(UserID, result);
					this.productShoppinCart.get(UserID).clear();
					return true;
				}
				
			}
		}
	
		return false;
	}

	

	@Override
	public Boolean clearProductMessage(String UserID) throws RemoteException {
		// TODO Auto-generated method stub
		this.productMessage.put(UserID, null);
		return true;
	}

	@Override
	public String getProductMessage(String UserID) throws RemoteException {
		// TODO Auto-generated method stub
		return this.productMessage.get(UserID);
	
	}

	@Override
	public List<Product> showShoppingcart(String UserID) throws RemoteException {
		// TODO Auto-generated method stub
		List<Product> productShoppingCartShow = new ArrayList<Product>();
		for (Map.Entry<String,Map<String, List<ProductItem>>> entry : this.productShoppinCart.entrySet()) 
		{
			if(entry.getKey().equals(UserID))
			{
				for (Entry<String, List<ProductItem>> productMapLocal : entry.getValue().entrySet()) 
				{
					productShoppingCartShow.add(new Product(productMapLocal.getKey(),this.productMap.get(productMapLocal.getKey()).getProduct() ,productMapLocal.getValue().size()));
				}
				
			}
		}
		return productShoppingCartShow;
	}

}

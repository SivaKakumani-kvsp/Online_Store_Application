package common;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pId;
	public String getpId() {
		return pId;
	}

	public ProductItem getProduct() {
		return product;
	}

	public int getQuantity() {
		return Quantity;
	}

	ProductItem product;
	public void setProduct(ProductItem product) {
		this.product = product;
	}

	int Quantity;

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Product(String pId, ProductItem product, int Quantity) throws RemoteException {
		super();
		this.pId = pId;
		this.product = product;
		this.Quantity = Quantity;
	}
	


}

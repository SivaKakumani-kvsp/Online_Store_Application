package common;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class ShoppingCart {

	
	private List<ProductItem> products;
	private String userID;
	private int cartQuantity;
	

	// constructor
	public ShoppingCart() {
		super();
		
	}

	// getters and setters
	

	public List<ProductItem> getProducts() {
		return this.products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}

	
	public String getTotalPrice() {
		return this.userID;
	}
	public void setTotalPrice(double totalPrice) {
		this.userID = userID;
	}
	public int getTotalQuantity() {
		return this.cartQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.cartQuantity = totalQuantity;
	}

	//method to add products into class
	public void addProduct(ProductItem product) {

		
		cartQuantity = this.products.size();

		// add a product to cart
		this.products.add(product);

		// increment number of products after adding a product
		this.cartQuantity++;

		
		System.out.println("Iten is added to cart." + "\n" + cartQuantity + " item(s) are in the cart."
				);

	}
}
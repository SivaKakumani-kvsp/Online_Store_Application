package common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

public class ProductItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String pType;
	String pName;
	BigDecimal pPrice;

	public ProductItem(String pType, String pName, BigDecimal pPrice) throws RemoteException {
		super();
		this.pName = pName;
		this.pType = pType;
		this.pPrice = pPrice;
	}
	

	public String getpType() {
		return pType;
	}


	public void setpType(String pType) {
		this.pType = pType;
	}


	public String getpName() {
		return pName;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public BigDecimal getpPrice() {
		return pPrice;
	}


	public void setpPrice(BigDecimal pPrice) {
		this.pPrice = pPrice;
	}


	public String getProductType() {
		return pType;
	}

	public String getProductName() {
		return pName;
	}

	public BigDecimal getProductPrice() {
		return pPrice;
	}


}

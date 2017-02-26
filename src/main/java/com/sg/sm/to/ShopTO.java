/**
 * 
 */
package com.sg.sm.to;

/**
 * @author Sandeep Garg
 *
 */
public class ShopTO {
	
	private String id;
	private String shopName;
	private AddressTO shopAddress;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public AddressTO getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(AddressTO shopAddress) {
		this.shopAddress = shopAddress;
	}

}

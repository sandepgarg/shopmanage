/**
 * 
 */
package com.sg.sm.to;

import com.sg.sm.pojo.Shop;
import com.sg.sm.pojo.ShopMaster;

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
	
	public static Shop convertTOToDTO(ShopTO shopTO) {
		Shop shop = new Shop();
		if(shopTO != null) {
			shop.setId(shopTO.getId());
			shop.setName(shopTO.getShopName());
			shop.setNumber(shopTO.getShopAddress().getNumber());
			shop.setPostCode(shopTO.getShopAddress().getPostCode());
		}
		return shop;
	}
	
	public static ShopTO convertDTOToTO(ShopMaster shop) {
		ShopTO shopTO = new ShopTO();
		if(shop != null) {
			shopTO.setId(shop.getId());
			shopTO.setShopName(shop.getName());
			AddressTO addTo = new AddressTO();
			addTo.setNumber(shop.getNumber());
			addTo.setPostCode(shop.getPostCode());
			shopTO.setShopAddress(addTo);
		}
		return shopTO;
	}

}

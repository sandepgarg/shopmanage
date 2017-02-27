/**
 * 
 */
package com.sg.sm.to;

import java.util.ArrayList;
import java.util.List;

import com.sg.sm.config.URLConstants;
import com.sg.sm.pojo.Shop;
import com.sg.sm.pojo.ShopMaster;
import com.sg.sm.to.common.Link;

/**
 * @author Sandeep Garg
 *
 */
public class ShopTO {
	protected List<Link> links = new ArrayList<Link>();
	
	private final int version = 1;
	
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
	
	/**
	 * @return the link
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(List<Link> links) {
		this.links = links;
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
			shopTO.addLinks();
		}
		return shopTO;
	}
	
	private void addLinks() {
		this.links.add(new Link("GET", "self", URLConstants.SHOP_LIST_SELF_URI));
		this.links.add(new Link("GET", "up", URLConstants.APP_URI));
	}
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

}

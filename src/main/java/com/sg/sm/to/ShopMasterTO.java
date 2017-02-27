/**
 * 
 */
package com.sg.sm.to;

import com.sg.sm.config.URLConstants;
import com.sg.sm.pojo.ShopMaster;
import com.sg.sm.to.common.Link;

/**
 * @author Sandeep Garg
 *
 */
public class ShopMasterTO extends ShopTO {
	
	private final int version = 1;
	
	private String shopLongitude;
	
	private String shopLatitude;

	/**
	 * @return the shopLongitude
	 */
	public String getShopLongitude() {
		return shopLongitude;
	}

	/**
	 * @param shopLongitude the shopLongitude to set
	 */
	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	/**
	 * @return the shopLatitude
	 */
	public String getShopLatitude() {
		return shopLatitude;
	}

	/**
	 * @param shopLatitude the shopLatitude to set
	 */
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
	public static ShopMasterTO convertDTOToTO(ShopMaster shop) {
		ShopMasterTO shopMasterTO = new ShopMasterTO();
		if(shop != null) {
			shopMasterTO.setId(shop.getId());
			shopMasterTO.setShopName(shop.getName());
			AddressTO addTo = new AddressTO();
			addTo.setNumber(shop.getNumber());
			addTo.setPostCode(shop.getPostCode());
			shopMasterTO.setShopAddress(addTo);
			shopMasterTO.setShopLongitude(shop.getLongitude());
			shopMasterTO.setShopLatitude(shop.getLatitud());
			shopMasterTO.addLinks();
		}
		return shopMasterTO;
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

/**
 * 
 */
package com.sg.sm.pojo;


/**
 * @author Sandeep Garg
 *
 */
public class ShopMaster implements Comparable<ShopMaster> {
	
	private String id;
	private String name;
	private String number;
	private String postCode;
	private String longitude;
	private String latitud;
	
	public ShopMaster() {
		
	}
	
	public ShopMaster(Shop shop) {
		setId(shop.getId());
		setName(shop.getName());
		setNumber(shop.getNumber());
		setPostCode(shop.getPostCode());
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitud
	 */
	public String getLatitud() {
		return latitud;
	}
	/**
	 * @param latitud the latitud to set
	 */
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	} 

	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		} else {
			if (! (obj instanceof ShopMaster)) {
				return false;
			} 
			ShopMaster shopMaster = (ShopMaster) obj;
			if((shopMaster.getId() != null && getId() == null) 
					|| (shopMaster.getId() == null && getId() != null) 
					|| (!shopMaster.getId().equalsIgnoreCase(getId()))) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return 17 + 3 * getId().hashCode();
	}

	@Override
	public int compareTo(ShopMaster shop) {
		return getId().compareTo(shop.getId());
	}
}

package com.sg.sm.util;

/**
 * 
 * @author Sandeep Garg
 *
 */
public class Location {
	String lat;
	String lng;

	public Location(String longitude, String latitude) {
		this.lng = longitude;
		this.lat = latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public String getLat() {
		return lat;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLat(String longitude) {
		this.lat = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLng() {
		return lng;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLng(String latitude) {
		this.lng = latitude;
	}

}

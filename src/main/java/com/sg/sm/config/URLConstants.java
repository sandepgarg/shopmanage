package com.sg.sm.config;

/**
 * 
 * @author Sandeep Garg
 *
 */
public interface URLConstants {
	
	public static final String BASE_URI = "";
	public static final String APP_URI = BASE_URI + "/";
	
	public static final String SHOP_LIST_URI = BASE_URI + "/shops";
	public static final String SHOP_LIST_SELF_URI = SHOP_LIST_URI + "/{shopId}";
	
	public static final String GENERIC_MEDIA_TYPE= "application/json";
	public static final String GENERIC_ERROR_MEDIA_TYPE = "application/vnd.sg.sm.error+json";;

	public static final String SHOP_MEDIA_TYPE = "application/vnd.sg.sm.shop+json";
	public static final String SHOP_ACCEPT_HEADERS = SHOP_MEDIA_TYPE + " "+ GENERIC_MEDIA_TYPE;
	
	public static final String GENERIC_COLLECTION_MEDIA_TYPE = "application/vnd.sg.collection+json";
	
	
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	

}

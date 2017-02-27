/**
 * 
 */
package com.sg.sm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.util.UriUtils;

import com.sg.sm.exception.ShopManageException;

/**
 * @author Sandeep Garg
 *
 */
public class AddressUtility {
	
	/**
	 * 
	 * @param address
	 * @return
	 */
	public static Location readWebService(String address) throws ShopManageException {
		Location locationPoint = null;
		try {
			String encoding ="UTF-8";
	        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="
	                        + UriUtils.encodeQuery(address, encoding) + "&sensor=true");
	        
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new ShopManageException("Failed : HTTP error code : " + conn.getResponseCode());
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	        String output = "", full = "";
	        while ((output = br.readLine()) != null) {
	            full += output;
	        }
	        
	        JSONObject json;
	        System.out.println(full);
	        try {

	            String lat, lon;
	            json = new JSONObject(full);
	            JSONObject geoMetryObject = new JSONObject();
	            JSONObject locations = new JSONObject();
	            JSONArray jarr = json.getJSONArray("results");
	            int i;
	            for (i = 0; i < jarr.length(); i++) {
	                json = jarr.getJSONObject(i);
	                geoMetryObject = json.getJSONObject("geometry");
	                locations = geoMetryObject.getJSONObject("location");
	                lat = "" + locations.getDouble("lat");
	                lon = ""+ locations.getDouble("lng");
	                locationPoint = new Location(lat, lon); 
	            }
	        } catch (Exception e) {
	        	throw new ShopManageException(e.getMessage());
	        }

	        conn.disconnect();
	    } catch (MalformedURLException e) {
	    	throw new ShopManageException(e.getMessage());
	    } catch (IOException e) {
	    	throw new ShopManageException(e.getMessage());
	    }
		return locationPoint;
		
	}

	/**
	 * Distance between two customer and shop
	 * 
	 * @param customer
	 * @param shop
	 * @return distance between customer and shop
	 */
	public static double calculateDistance(Location customer, Location shop ) throws ShopManageException{
		try{
			double custLat = Double.valueOf(customer.getLat()); 
			double custLng = Double.valueOf(customer.getLng());
			double shopLat = Double.valueOf(shop.getLat());
			double shopLng = Double.valueOf(shop.getLng());

		    double radiusOfEarth = 3958.75; // in miles 3958.75, in kilometer 6371 

		    double latDiff = Math.toRadians(shopLat-custLat);
		    double lngDiff = Math.toRadians(shopLng-custLng);

		    double var = Math.pow(Math.sin(latDiff / 2), 2) + Math.pow(Math.sin(lngDiff / 2), 2)
		    		        * Math.cos(Math.toRadians(custLat)) * Math.cos(Math.toRadians(shopLat));

		    double distance = radiusOfEarth * 2 * Math.atan2(Math.sqrt(var), Math.sqrt(1-var));

		    return distance; 
			
		} catch (RuntimeException exp) {
			throw new ShopManageException(exp.getMessage());
		}
		
	}


}

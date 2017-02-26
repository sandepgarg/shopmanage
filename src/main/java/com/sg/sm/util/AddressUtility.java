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

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Sandeep Garg
 *
 */
public class AddressUtility {
	
	public static Location getLocationByAddress(String locationAddress) {
		Location location = new Location();
		String address = "Sayaji Hotel, Near balewadi stadium, pune";
		if(locationAddress != null) {
			locationAddress = address;
		}
        String locationAddressInURL = locationAddress.replaceAll(" ", "%20");
        
        String str = "http://maps.googleapis.com/maps/api/geocode/json?address="
                + locationAddressInURL + "&sensor=true";
        
        Location ss = readWebService(address);
       /* JSONObject json;
        try {

            String lat, lon;
            json = new JSONObject(ss);
            JSONObject geoMetryObject = new JSONObject();
            JSONObject locations = new JSONObject();
            JSONArray jarr = json.getJSONArray("results");
            int i;
            for (i = 0; i < jarr.length(); i++) {
                json = jarr.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                lat = locations.getString("lat");
                lon = locations.getString("lng");

                locationPoint = Utils.getGeoPoint(Double.parseDouble(lat),
                        Double.parseDouble(lon));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        location.setLng("2434211423");
        location.setLat("345223456");
        return location;
    }

	private static Location readWebService(String address) {
		Location locationPoint = null;
		try {
			String encoding ="UTF-8";
	        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?address="
	                        + UriUtils.encodeQuery(address, encoding) + "&sensor=true");
	        
			//address = UriUtils.encodeQuery(address, encoding);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Accept", "application/json");

	        if (conn.getResponseCode() != 200) {
	            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());//TODO
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

	        String output = "", full = "";
	        while ((output = br.readLine()) != null) {
	            System.out.println(output);
	            full += output;
	        }
	       // gson.getResults().get(0).getFormatted_address(),
	        
	        ///////////////////////////////////////////////////////////////////////////////////////////
	        JSONObject json;
	        System.err.println(full);
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
	                lat = locations.getString("lat");
	                lon = locations.getString("lng");
	                locationPoint = new Location(); 
	                locationPoint.setLat(lat);
	                locationPoint.setLng(lon);

	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        ///////////////////////////////////////////////////////////////////////////////////////////
	        ObjectMapper mapper = new ObjectMapper();
//	        Shop shop = mapper.readValue(full, Shop.class);
	        
	        float[] distance = new float[1];
	        // distance[0] is now the distance between these lat/lons in meters
	        if (distance[0] < 2.0) {
	            // your code...
	        }

	        /*PincodeVerify gson = new Gson().fromJson(full, PincodeVerify.class); 
	        response = new IsPincodeSupportedResponse(new PincodeVerifyConcrete(
	                gson.getResults().get(0).getFormatted_address(), 
	                gson.getResults().get(0).getGeometry().getLocation().getLat(),
	                gson.getResults().get(0).getGeometry().getLocation().getLng())) ;
	        try {
	            String address = response.getAddress();
	            Double latitude = response.getLatitude(), longitude = response.getLongitude();
	            if (address == null || address.length() <= 0) {
	                log.error("Address is null");
	            }
	        } catch (NullPointerException e) {
	            log.error("Address, latitude on longitude is null");
	        }*/
	        conn.disconnect();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return locationPoint;
		
	}

}

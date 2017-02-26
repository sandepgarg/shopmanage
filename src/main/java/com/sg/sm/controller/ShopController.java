package com.sg.sm.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.sm.exception.ShopManageException;
import com.sg.sm.service.ShopService;
import com.sg.sm.to.ShopTO;

@RestController
public class ShopController {
	
	public static final String GENERIC_MEDIA_TYPE= "application/json";
	public static final String GENERIC_COLLECTION_MEDIA_TYPE = "application/vnd.sg.collection+json";
	public static final String SHOP_MEDIA_TYPE = "application/vnd.sg.sm.shop+json";
	public static final String SHOP_ACCEPT_HEADERS = SHOP_MEDIA_TYPE + " "+ GENERIC_MEDIA_TYPE;
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/shops", method=RequestMethod.GET,produces = {GENERIC_COLLECTION_MEDIA_TYPE})
	public List<ShopTO> handleGetNearByShop(
			@RequestParam(value = "longitude", required = false)  String longitude,
			@RequestParam(value = "latitude", required = false)  String latitude) {
		
		if(longitude!=null || latitude!=null) {
			return null;//"Shop handleGetAllShops !!!" + longitude + " : " + latitude;
		}
		
		List<ShopTO> shopCollection=new ArrayList<ShopTO>();
		try {
			shopCollection = shopService.getShopCollection();
		} catch (ShopManageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopCollection;
		
	}
	
	@RequestMapping(value="/shops", method=RequestMethod.POST, consumes = {SHOP_MEDIA_TYPE, GENERIC_MEDIA_TYPE }, produces = {SHOP_MEDIA_TYPE})
	public String handleSaveShop(HttpServletRequest request, @RequestBody ShopTO shopTO) {
		ShopTO savedShopTO = null;
		if(shopTO!= null) {
			try {
				savedShopTO = shopService.saveShop(shopTO);
				} catch (ShopManageException e) {
					return e.getMessage();
				}
		}
		return "Shop handleSaveShop !!!";
	}
	
	
	@RequestMapping(value="/shops/{shopId}", method=RequestMethod.GET)
	public String handleGetShop() {
		return "Shop handleGetAShop !!!";
	}
	

	
	
}


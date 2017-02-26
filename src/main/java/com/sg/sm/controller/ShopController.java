package com.sg.sm.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.sm.exception.ShopManageException;
import com.sg.sm.service.ShopService;
import com.sg.sm.to.ShopMasterTO;
import com.sg.sm.to.ShopTO;

@RestController
public class ShopController {
	
	public static final String INTERNAL_SERVER_MESSAGE = "There is some error. Please contact adminstrator.";
	
	public static final String GENERIC_MEDIA_TYPE= "application/json";
	public static final String GENERIC_COLLECTION_MEDIA_TYPE = "application/vnd.sg.collection+json";
	public static final String SHOP_MEDIA_TYPE = "application/vnd.sg.sm.shop+json";
	public static final String SHOP_ACCEPT_HEADERS = SHOP_MEDIA_TYPE + " "+ GENERIC_MEDIA_TYPE;
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/shops", method=RequestMethod.GET,produces = {GENERIC_COLLECTION_MEDIA_TYPE})
	public ResponseEntity<?> handleGetNearByShop(
			@RequestParam(value = "longitude", required = false)  String longitude,
			@RequestParam(value = "latitude", required = false)  String latitude) {
		
		if(longitude!=null || latitude!=null) {
			ShopMasterTO shop;
			try {
				shop = shopService.getShop(longitude, latitude);
			} catch (ShopManageException e) {
				ResponseEntity<String> shopResponseEntity = new ResponseEntity<>(INTERNAL_SERVER_MESSAGE, null, HttpStatus.INTERNAL_SERVER_ERROR);
				return shopResponseEntity;
			}
			if(shop!= null) {
				ResponseEntity<ShopTO> shopResponse =  new ResponseEntity<ShopTO>(shop, null, HttpStatus.OK);
				return shopResponse;		
			} else {
				ResponseEntity<String> shopResponseEntity = new ResponseEntity<>("No shop found.", null, HttpStatus.NOT_FOUND);
				return shopResponseEntity;
			}
		}
		
		List<ShopTO> shopCollection=new ArrayList<ShopTO>();
		try {
			shopCollection = shopService.getShopCollection();
		} catch (ShopManageException e) {
			
		}
		ResponseEntity<List<ShopTO>> shopCollectionResponse =  new ResponseEntity<List<ShopTO>>(shopCollection, null, HttpStatus.OK);
		return shopCollectionResponse;
		
	}
	
	@RequestMapping(value="/shops", method=RequestMethod.POST, consumes = {SHOP_MEDIA_TYPE, GENERIC_MEDIA_TYPE }, produces = {SHOP_MEDIA_TYPE})
	public ResponseEntity<?> handleSaveShop(HttpServletRequest request, @RequestBody ShopTO shopTO) {
		ShopTO savedShopTO = null;
		if(shopTO!= null) {
			try {
				savedShopTO = shopService.saveShop(shopTO);
				} catch (ShopManageException e) {
					ResponseEntity<String> shopResponseEntity = new ResponseEntity<>(INTERNAL_SERVER_MESSAGE, null, HttpStatus.INTERNAL_SERVER_ERROR);
					return shopResponseEntity;
				}
		}
		if(savedShopTO !=  null) {
			ResponseEntity<ShopTO> shopResponseEntity = new ResponseEntity<>(savedShopTO, null, HttpStatus.OK);
			return shopResponseEntity;
		} else {
			ResponseEntity<String> messageResponseEntity = new ResponseEntity<>("Shop can not be saved", null, HttpStatus.BAD_REQUEST);
			return messageResponseEntity;
		}
	}
	
	
	@RequestMapping(value="/shops/{shopId}", method=RequestMethod.GET)
	public ResponseEntity<?> handleGetShop(HttpServletRequest request, @PathVariable("shopId") String shopId) {
		ShopMasterTO shopMaster = null;
		if(shopId == null) {
			ResponseEntity<String> messageResponseEntity = new ResponseEntity<>("Shop id is not proper, please provide a appropriate shop Id.", null, HttpStatus.BAD_REQUEST);
			return messageResponseEntity;
		}
		try {
			shopMaster = shopService.getShop(shopId);
		} catch (ShopManageException e) {
			ResponseEntity<String> shopResponseEntity = new ResponseEntity<>(INTERNAL_SERVER_MESSAGE, null, HttpStatus.INTERNAL_SERVER_ERROR);
			return shopResponseEntity;
		}
		if(shopMaster != null) {
			ResponseEntity<ShopMasterTO> shopResponseEntity = new ResponseEntity<>(shopMaster, null, HttpStatus.OK);
			return shopResponseEntity;
		} else {
			ResponseEntity<String> messageResponseEntity = new ResponseEntity<>("Shop of id = " + shopId + " is not found.", null, HttpStatus.NOT_FOUND);
			return messageResponseEntity;
		}
		
	}
	
}


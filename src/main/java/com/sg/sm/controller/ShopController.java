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

import com.sg.sm.config.URLConstants;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.service.ShopService;
import com.sg.sm.to.ShopMasterTO;
import com.sg.sm.to.ShopTO;
import com.sg.sm.to.common.ResourceCollection;
import com.sg.sm.to.common.Error;
import com.sg.sm.util.CommonUtil;

@RestController
public class ShopController {
	
	public static final String INTERNAL_SERVER_MESSAGE = "There is some error. Please contact adminstrator.";
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/shops", method=RequestMethod.GET,produces = {URLConstants.GENERIC_COLLECTION_MEDIA_TYPE})
	public ResponseEntity<?> handleGetNearByShop(
			@RequestParam(value = "longitude", required = false)  String longitude,
			@RequestParam(value = "latitude", required = false)  String latitude) {
		
		if(longitude!=null || latitude!=null) {
			ShopMasterTO shop;
			try {
				shop = shopService.getShop(longitude, latitude);
			} catch (ShopManageException e) {
				Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0, INTERNAL_SERVER_MESSAGE);
				ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
				return errorResponse;
			}
			if(shop!= null) {
				ResponseEntity<ShopTO> shopResponse =  new ResponseEntity<ShopTO>(shop, null, HttpStatus.OK);
				return shopResponse;		
			} else {
				
				Error error = new Error(HttpStatus.NOT_FOUND.value(), 0, "No shop found.");
				ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.NOT_FOUND);
				return errorResponse;
			
			}
		}
		
		ResourceCollection<ShopTO> collection = new ResourceCollection<ShopTO>();
		try {
			List<ShopTO> shopCollection = new ArrayList<ShopTO>();
			shopCollection = shopService.getShopCollection();
			collection.setItems(shopCollection);
		} catch (ShopManageException e) {
			Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0, INTERNAL_SERVER_MESSAGE);
			ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, null, HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
		ResponseEntity<ResourceCollection<ShopTO>> shopCollectionResponse =  new ResponseEntity<ResourceCollection<ShopTO>>(collection, CommonUtil.getErrHeader(), HttpStatus.OK);
		return shopCollectionResponse;
		
	}
	
	@RequestMapping(value="/shops", method=RequestMethod.POST, consumes = {URLConstants.SHOP_MEDIA_TYPE, URLConstants.GENERIC_MEDIA_TYPE }, produces = {URLConstants.SHOP_MEDIA_TYPE})
	public ResponseEntity<?> handleSaveShop(HttpServletRequest request, @RequestBody ShopTO shopTO) {
		ShopTO savedShopTO = null;
		if(shopTO!= null) {
			try {
				
				savedShopTO = shopService.saveShop(shopTO);
				
				} catch (ShopManageException e) {
					Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0, INTERNAL_SERVER_MESSAGE);
					ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
					return errorResponse;
				}
		}
		if(savedShopTO !=  null) {
			
			ResponseEntity<ShopTO> shopResponseEntity = new ResponseEntity<>(savedShopTO, null, HttpStatus.OK);
			return shopResponseEntity;
		
		} else {

			Error error = new Error(HttpStatus.CONFLICT.value(), 0, "Shop with this id already exist.", "Save the shop with different Id");
			ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.CONFLICT);
			return errorResponse;
			
		}
	}
	
	
	@RequestMapping(value="/shops/{shopId}", method=RequestMethod.GET)
	public ResponseEntity<?> handleGetShop(HttpServletRequest request, @PathVariable("shopId") String shopId) {
		ShopMasterTO shopMaster = null;
		if(shopId == null) {
			ResponseEntity<String> messageResponseEntity = new ResponseEntity<>("Shop id is not proper, please provide a appropriate shop Id.", CommonUtil.getErrHeader(), HttpStatus.BAD_REQUEST);
			return messageResponseEntity;
		}
		try {
			shopMaster = shopService.getShop(shopId);
		} catch (ShopManageException e) {
			Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), 0, INTERNAL_SERVER_MESSAGE);
			ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.INTERNAL_SERVER_ERROR);
			return errorResponse;
		}
		if(shopMaster != null) {
			ResponseEntity<ShopMasterTO> shopResponseEntity = new ResponseEntity<>(shopMaster, null, HttpStatus.OK);
			return shopResponseEntity;
		} else {
			
			Error error = new Error(HttpStatus.NOT_FOUND.value(), 0, "Shop of id = " + shopId + " is not found.");
			ResponseEntity<Error> errorResponse =  new ResponseEntity<Error>(error, CommonUtil.getErrHeader(), HttpStatus.NOT_FOUND);
			return errorResponse;
		}
		
	}
	
}


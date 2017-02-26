package com.sg.sm.service;

import java.util.List;

import com.sg.sm.exception.ShopManageException;
import com.sg.sm.to.ShopMasterTO;
import com.sg.sm.to.ShopTO;

/**
 * @author Sandeep Garg
 *
 */
public interface ShopService {
	
	/**
	 * 
	 * @param shopTO
	 * @return
	 * @throws ShopManageException
	 */
	ShopMasterTO saveShop(ShopTO shopTO) throws ShopManageException;
	
	/**
	 * 
	 * @return
	 * @throws ShopManageException
	 */
	List<ShopTO> getShopCollection() throws ShopManageException;
	
	/**
	 * 
	 * @return
	 * @throws ShopManageException
	 */
	ShopMasterTO getShop(String id) throws ShopManageException;
	
	/**
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 * @throws ShopManageException
	 */
	ShopMasterTO getShop(String longitude, String latitude) throws ShopManageException;

}

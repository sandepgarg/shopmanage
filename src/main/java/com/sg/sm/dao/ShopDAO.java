/**
 * 
 */
package com.sg.sm.dao;

import java.util.List;

import com.sg.sm.exception.ShopDAOException;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.pojo.ShopMaster;

/**
 * @author Sandeep Garg
 *
 */
public interface ShopDAO {
	
	/**
	 * 
	 * @param shop
	 * @return
	 * @throws ShopManageException
	 */
	ShopMaster saveShop(ShopMaster shop) throws ShopDAOException;
	
	/**
	 * 
	 * @return
	 * @throws ShopManageException
	 */
	List<ShopMaster> getShopCollection() throws ShopManageException;
	
	/**
	 * 
	 * @return
	 * @throws ShopManageException
	 */
	ShopMaster getShop(String id) throws ShopManageException;
	
}

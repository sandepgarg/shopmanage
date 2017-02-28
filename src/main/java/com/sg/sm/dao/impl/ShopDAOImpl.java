/**
 * 
 */
package com.sg.sm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sg.sm.dao.ShopDAO;
import com.sg.sm.exception.ShopDAOException;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.pojo.ShopMaster;

/**
 * @author Sandeep Garg
 *
 */
@Component
public class ShopDAOImpl implements ShopDAO {
	
	private static Map<String, ShopMaster> listOfShop = new HashMap<String, ShopMaster>();
	
	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#saveShop(com.sg.sm.to.ShopTO)
	 */
	@Override
	public ShopMaster saveShop(ShopMaster shopMaster) throws ShopDAOException {
		
		if(!listOfShop.containsValue(shopMaster)){
			listOfShop.put(shopMaster.getId(), shopMaster);
		} else {
			return null;
		}
		return listOfShop.get(shopMaster.getId());
	}

	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#getShopCollection()
	 */
	@Override
	public List<ShopMaster> getShopCollection() throws ShopManageException {
		return new ArrayList<ShopMaster>(listOfShop.values());
	}

	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#getShop(java.lang.String)
	 */
	@Override
	public ShopMaster getShop(String id) throws ShopManageException {
		return listOfShop.get(id);
	}

	
}

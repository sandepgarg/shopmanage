/**
 * 
 */
package com.sg.sm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sg.sm.dao.ShopDAO;
import com.sg.sm.exception.ShopDAOException;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.pojo.Shop;
import com.sg.sm.pojo.ShopMaster;

/**
 * @author Sandeep Garg
 *
 */
@Component
public class ShopDAOImpl implements ShopDAO {
	private static List<ShopMaster> listOfShop = new ArrayList<ShopMaster>();
	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#saveShop(com.sg.sm.to.ShopTO)
	 */
	@Override
	public ShopMaster saveShop(ShopMaster shopMaster) throws ShopDAOException {
		if(!listOfShop.contains(shopMaster)){
			listOfShop.add(shopMaster);
		}
		return shopMaster;
	}

	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#getShopCollection()
	 */
	@Override
	public List<ShopMaster> getShopCollection() throws ShopManageException {
		// TODO Auto-generated method stub
		return listOfShop;
	}

	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#getShop(java.lang.String)
	 */
	@Override
	public ShopMaster getShop(String id) throws ShopManageException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sg.sm.dao.ShopDAO#getShop(java.lang.String, java.lang.String)
	 */
	@Override
	public ShopMaster getShop(String longitude, String latitude)
			throws ShopManageException {
		// TODO Auto-generated method stub
		return null;
	}

}

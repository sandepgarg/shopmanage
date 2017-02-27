/**
 * 
 */
package com.sg.sm.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.sm.dao.ShopDAO;
import com.sg.sm.exception.ShopDAOException;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.pojo.Shop;
import com.sg.sm.pojo.ShopMaster;
import com.sg.sm.service.ShopService;
import com.sg.sm.to.ShopMasterTO;
import com.sg.sm.to.ShopTO;
import com.sg.sm.util.AddressUtility;
import com.sg.sm.util.Location;

/**
 * @author Sandeep Garg
 *
 */
@Component
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDAO shopDao;

	@Override
	public ShopMasterTO saveShop(ShopTO shopTO) throws ShopManageException {
		Shop shop = ShopTO.convertTOToDTO(shopTO);
		ShopMaster shopMaster = new ShopMaster(shop);
		String address = shopTO.getShopAddress().getPostCode();
		//Validate pin code
		Location location = AddressUtility.readWebService(address);
		
		shopMaster.setLongitude(""+ location.getLat());
		shopMaster.setLatitud("" + location.getLng());
		
		try {
			shopDao.saveShop(shopMaster);
		} catch (ShopDAOException e) {
			throw new ShopManageException(e.getMessage());
		}
		
		return ShopMasterTO.convertDTOToTO(shopDao.getShop(shop.getId()));
	}

	@Override
	public List<ShopTO> getShopCollection() throws ShopManageException {
		List<ShopTO> allShopTO = new ArrayList<ShopTO>();
		List<ShopMaster> allShop = shopDao.getShopCollection();
		Collections.sort(allShop);
		for(ShopMaster shop: allShop) {
			allShopTO.add(ShopTO.convertDTOToTO(shop));
		}
		
		return allShopTO;
	}

	@Override
	public ShopMasterTO getShop(String id) throws ShopManageException {
		ShopMaster shopMaster = shopDao.getShop(id);
		if(shopMaster != null) {
			return ShopMasterTO.convertDTOToTO(shopMaster);
		}
		return null;
	}

	@Override
	public ShopMasterTO getShop(String longitude, String latitude) throws ShopManageException {
		ShopMaster shopMaster = null;
		double minimumDistance = -1;
		Location customerLocation = new Location(longitude, latitude);
		List<ShopMaster> allShop = shopDao.getShopCollection();
		for(ShopMaster shop: allShop) {
			Location shopLocation = new Location(shop.getLongitude(), shop.getLatitud());
			double distance = AddressUtility.calculateDistance(customerLocation, shopLocation);
			if(minimumDistance == -1 || distance < minimumDistance) {
				minimumDistance = distance;
				shopMaster = shop;
			} 
		}
		if(shopMaster != null) {
			return ShopMasterTO.convertDTOToTO(shopMaster);
		} else {
			return null;
		}
	}


}

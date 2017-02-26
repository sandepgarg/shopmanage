/**
 * 
 */
package com.sg.sm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sg.sm.dao.ShopDAO;
import com.sg.sm.exception.ShopDAOException;
import com.sg.sm.exception.ShopManageException;
import com.sg.sm.pojo.Shop;
import com.sg.sm.pojo.ShopMaster;
import com.sg.sm.service.ShopService;
import com.sg.sm.to.AddressTO;
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
		Shop shop = convertTOToDTO(shopTO);
		ShopMaster shopMaster = new ShopMaster(shop);
		String address = "Sayaji Hotel, Near balewadi stadium, pune";;
		Location location = AddressUtility.getLocationByAddress(address);
		
		shopMaster.setLongitude(""+ location.getLat());
		shopMaster.setLatitud("" + location.getLng());
		
		try {
			shopDao.saveShop(shopMaster);
		} catch (ShopDAOException e) {
			throw new ShopManageException();//TODO
		}
		return null;//TODO
	}

	@Override
	public List<ShopTO> getShopCollection() throws ShopManageException {
		List<ShopTO> allShopTO = new ArrayList<ShopTO>();
		List<ShopMaster> allShop = shopDao.getShopCollection();
		for(ShopMaster shop: allShop) {
			allShopTO.add(convertDTOToTO(shop));
		}
		
		return allShopTO;
	}

	@Override
	public ShopMasterTO getShop(String id) throws ShopManageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ShopMasterTO getShop(String longitude, String latitude)
			throws ShopManageException {
		// TODO Auto-generated method stub
		return null;
	}

	private Shop convertTOToDTO(ShopTO shopTO) {
		Shop shop = new Shop();
		shop.setId(shopTO.getId());
		shop.setName(shopTO.getShopName());
		shop.setNumber(shopTO.getShopAddress().getNumber());
		shop.setPostCode(shopTO.getShopAddress().getPostCode());
		return shop;
	}
	
	private ShopTO convertDTOToTO(ShopMaster shop) {
		ShopTO shopTO = new ShopTO();
		shopTO.setId(shop.getId());
		shopTO.setShopName(shop.getName());
		AddressTO addTo = new AddressTO();
		addTo.setNumber(shop.getName());
		addTo.setPostCode(shop.getPostCode());
		shopTO.setShopAddress(addTo);
		return shopTO;
	}
}

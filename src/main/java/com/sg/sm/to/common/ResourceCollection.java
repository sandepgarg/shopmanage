/**
 * 
 */
package com.sg.sm.to.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sandeep Garg
 *
 */
public class ResourceCollection<T> {
	
	private final int version = 1;
	private List<Link> links = new ArrayList<Link>();

	private List<T> items = new ArrayList<T>();

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * @return the items
	 */
	public List<T> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<T> items) {
		this.items = items;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}  
	
}

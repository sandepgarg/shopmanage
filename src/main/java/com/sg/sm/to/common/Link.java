/**
 * 
 */
package com.sg.sm.to.common;

import com.sg.sm.util.CommonUtil;

/**
 * @author sinsae
 *
 */
public class Link {
    private String method;
    private String rel;
    private String uri;
    private String href;
    
    public Link(String method, String rel, String uri)
    {
    	this(method, rel, uri, CommonUtil.getBaseURL() + uri);
        if (uri.length() > 0 && !uri.startsWith("/")) {
            throw new IllegalArgumentException("uri must be \"\" or start with '/'");
        }
    }

    public Link(String method, String rel, String uri, String href)
    {
        super();
        setMethod(method);
        setRel(rel);
        setUri(uri); 
        setHref(href);
    }
    
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}

}

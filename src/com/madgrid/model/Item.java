package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
	
	public static final int ITEM_TYPE_ONLY_CREDITS = 2;
	public static final int ITEM_TYPE_ONLY_BITCOINS = 4;
	public static final int ITEM_TYPE_CREDITS_AND_BITCOINS = 5;
	
	private Integer id;
	private Date created;
	private Date modified;
	private Integer type;
	private Integer credits;
	private String name;
	private Double bitcoins;
	private String htmlDescription;
	private String picture1Url;
	private String virtualPath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getBitcoins() {
		return bitcoins;
	}
	public void setBitcoins(Double bitcoins) {
		this.bitcoins = bitcoins;
	}
	public String getHtmlDescription() {
		return htmlDescription;
	}
	public void setHtmlDescription(String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}
	public String getPicture1Url() {
		return picture1Url;
	}
	public void setPicture1Url(String picture1Url) {
		this.picture1Url = picture1Url;
	}
	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	
	
	
}

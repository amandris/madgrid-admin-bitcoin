package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Blockcypher implements Serializable{

	private Integer id;
	private Date created;
	private Date expiration;
	private User user;
	private Integer userId;
	private String address;
	private Boolean received;
	private String blockcypherId;
	
	
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
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getReceived() {
		return received;
	}
	public void setReceived(Boolean received) {
		this.received = received;
	}
	public String getBlockcypherId() {
		return blockcypherId;
	}
	public void setBlockcypherId(String blockcypherId) {
		this.blockcypherId = blockcypherId;
	}
	
	
	
	
}

package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class AffiliationActivity implements Serializable{

	
	public static final int AFFILIATION_ACTIVITY_USER_REGISTRATION = 1;
	public static final int AFFILIATION_ACTIVITY_USER_CREDITS = 2;
	
	private Integer id;
	private Integer affiliationUserId;
	private Integer userId;
	private Date created;
	private Integer type;
	private Double totalBitcoins;
	private Double assignedBitcoins;
	private boolean alreadyPayed;
	private AffiliationUser affiliationUser;
	private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAffiliationUserId() {
		return affiliationUserId;
	}
	public void setAffiliationUserId(Integer affiliationUserId) {
		this.affiliationUserId = affiliationUserId;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getTotalBitcoins() {
		return totalBitcoins;
	}
	public void setTotalBitcoins(Double totalBitcoins) {
		this.totalBitcoins = totalBitcoins;
	}
	public Double getAssignedBitcoins() {
		return assignedBitcoins;
	}
	public void setAssignedBitcoins(Double assignedBitcoins) {
		this.assignedBitcoins = assignedBitcoins;
	}
	public AffiliationUser getAffiliationUser() {
		return affiliationUser;
	}
	public void setAffiliationUser(AffiliationUser affiliationUser) {
		this.affiliationUser = affiliationUser;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean getAlreadyPayed() {
		return alreadyPayed;
	}
	public void setAlreadyPayed(boolean alreadyPayed) {
		this.alreadyPayed = alreadyPayed;
	}
	
	
}

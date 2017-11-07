package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Win implements Serializable{

	private Integer id;
	private Date created;
	private Date modified;
	private User user;
	private Integer userId;
	private Item item;
	private Integer itemId;
	private Integer boughtBoxes;
	private Double moneySpent;
	private Boolean deliveryRequested;
	private Boolean itemSent;
	private Boolean isOnlyCredits;
	private String bitcoinAddress;
	private String transactionId;
	private Integer gridType;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Integer getBoughtBoxes() {
		return boughtBoxes;
	}
	public void setBoughtBoxes(Integer boughtBoxes) {
		this.boughtBoxes = boughtBoxes;
	}
	public Double getMoneySpent() {
		return moneySpent;
	}
	public void setMoneySpent(Double moneySpent) {
		this.moneySpent = moneySpent;
	}
	public Boolean getItemSent() {
		return itemSent;
	}
	public void setItemSent(Boolean itemSent) {
		this.itemSent = itemSent;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Boolean getIsOnlyCredits() {
		return isOnlyCredits;
	}
	public void setIsOnlyCredits(Boolean isOnlyCredits) {
		this.isOnlyCredits = isOnlyCredits;
	}
	public String getBitcoinAddress() {
		return bitcoinAddress;
	}
	public void setBitcoinAddress(String bitcoinAddress) {
		this.bitcoinAddress = bitcoinAddress;
	}
	public Boolean getDeliveryRequested() {
		return deliveryRequested;
	}
	public void setDeliveryRequested(Boolean deliveryRequested) {
		this.deliveryRequested = deliveryRequested;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getGridType() {
		return gridType;
	}
	public void setGridType(Integer gridType) {
		this.gridType = gridType;
	}
	
			
}

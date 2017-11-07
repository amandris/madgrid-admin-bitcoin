package com.madgrid.admin.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;


public class WinForm extends ValidatorForm implements Serializable{

	private Integer id;
	private Date modified;
	private Boolean itemSent;
	private Boolean deliveryRequested;
	private String transactionId;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Boolean getItemSent() {
		return itemSent;
	}
	public void setItemSent(Boolean itemSent) {
		this.itemSent = itemSent;
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
	
		
}

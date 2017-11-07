package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class AffiliationPayment implements Serializable{

	
	private Integer id;
	private Integer affiliationUserId;
	private Date created;
	private Double bitcoins;
	private AffiliationUser affiliationUser;
	private String transactionId;
	
	
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
	public Double getBitcoins() {
		return bitcoins;
	}
	public void setBitcoins(Double bitcoins) {
		this.bitcoins = bitcoins;
	}

	public AffiliationUser getAffiliationUser() {
		return affiliationUser;
	}
	public void setAffiliationUser(AffiliationUser affiliationUser) {
		this.affiliationUser = affiliationUser;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}

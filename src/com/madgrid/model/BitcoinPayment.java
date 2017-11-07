package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class BitcoinPayment implements Serializable{
	
	private Integer id;
	private String transactionId;
	private String created;
	private Date createdDate;
	private String receiveAddress;
	private String payerId;
	private Double bitcoins;
	private String credits;
	private Integer userId;
	private String loginHash;
	private String ip;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public Double getBitcoins() {
		return bitcoins;
	}
	public void setBitcoins(Double bitcoins) {
		this.bitcoins = bitcoins;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLoginHash() {
		return loginHash;
	}
	public void setLoginHash(String loginHash) {
		this.loginHash = loginHash;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
	
}

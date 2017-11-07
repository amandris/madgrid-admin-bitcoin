package com.madgrid.admin.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

public class AffiliationUserForm extends ValidatorForm implements Serializable{

	private Integer id;
	private Date created;
	private Date modified;
	private String login;
	private String password;
	private String name;
	private String url;
	private String bitcoinAddress;
	private String email;
	private Double percentage;
	private boolean askedForTransfer;
	private boolean paymentRequestValidated;
	
	
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
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBitcoinAddress() {
		return bitcoinAddress;
	}
	public void setBitcoinAddress(String bitcoinAddress) {
		this.bitcoinAddress = bitcoinAddress;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public boolean getAskedForTransfer() {
		return askedForTransfer;
	}
	public void setAskedForTransfer(boolean askedForTransfer) {
		this.askedForTransfer = askedForTransfer;
	}
	public boolean isPaymentRequestValidated() {
		return paymentRequestValidated;
	}
	public void setPaymentRequestValidated(boolean paymentRequestValidated) {
		this.paymentRequestValidated = paymentRequestValidated;
	}
	
	
}

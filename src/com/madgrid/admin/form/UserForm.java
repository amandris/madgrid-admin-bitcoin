package com.madgrid.admin.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorForm;

import com.madgrid.model.AffiliationUser;

public class UserForm extends ValidatorForm implements Serializable{

	private Integer id;
	private String login;
	private String password;
	private String email;
	private String canonicalEmail;
	private String promoCode;
	private String validationCode;
	private String bitcoinAddress;
	private Boolean active;
	private Boolean validated;
	private Integer credits;
	private Boolean isBeginner;
	private Boolean autoPay;
	private Integer affiliatedUsers;

	private Boolean isFraudulent;
	private Boolean isSubscribed;
	
	private Boolean requestPrizeSubscribed;
	private Boolean bitcoinSentSubscribed;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}
	public Integer getCredits() {
		return credits;
	}
	public void setCredits(Integer credits) {
		this.credits = credits;
	}
	public Boolean getIsBeginner() {
		return isBeginner;
	}
	public void setIsBeginner(Boolean isBeginner) {
		this.isBeginner = isBeginner;
	}
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
	public Boolean getIsFraudulent() {
		return isFraudulent;
	}
	public void setIsFraudulent(Boolean isFraudulent) {
		this.isFraudulent = isFraudulent;
	}
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Integer getAffiliatedUsers() {
		return affiliatedUsers;
	}
	public void setAffiliatedUsers(Integer affiliatedUsers) {
		this.affiliatedUsers = affiliatedUsers;
	}
	

	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	public String getCanonicalEmail() {
		return canonicalEmail;
	}
	public void setCanonicalEmail(String canonicalEmail) {
		this.canonicalEmail = canonicalEmail;
	}
	public Boolean getRequestPrizeSubscribed() {
		return requestPrizeSubscribed;
	}
	public void setRequestPrizeSubscribed(Boolean requestPrizeSubscribed) {
		this.requestPrizeSubscribed = requestPrizeSubscribed;
	}
	public Boolean getBitcoinSentSubscribed() {
		return bitcoinSentSubscribed;
	}
	public void setBitcoinSentSubscribed(Boolean bitcoinSentSubscribed) {
		this.bitcoinSentSubscribed = bitcoinSentSubscribed;
	}
	public String getBitcoinAddress() {
		return bitcoinAddress;
	}
	public void setBitcoinAddress(String bitcoinAddress) {
		this.bitcoinAddress = bitcoinAddress;
	}
	public Boolean getAutoPay() {
		return autoPay;
	}
	public void setAutoPay(Boolean autoPay) {
		this.autoPay = autoPay;
	}
	
	
}

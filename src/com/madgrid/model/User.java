package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{

	private Integer id;
	private Date created;
	private Date modified;
	private String login;
	private String password;
	private String email;
	private String canonicalEmail;
	private String recomendedUser;
	private String promoCode;
	private String bitcoinAddress;
	private String buyCreditsPromoCode;
	private String buyCreditsPromoCodeText;
	private Boolean active;
	private Boolean validated;
	private String validationCode;
	private Integer credits;
	private Boolean isBeginner;
	private Integer affiliatedUsers;
	private String ip;
	private Boolean isFraudulent;
	private Date lastLogin;
	private Boolean isSubscribed;
	private Boolean autoPay;
	
	private Boolean requestPrizeSubscribed;
	private Boolean bitcoinSentSubscribed;
	
	private Integer statisticsPlayedGames;
	private Integer statisticsWonGames;
	private Integer statisticsBoughtBoxes;
	private Integer statisticsUsedCredits;
	
	private String codeForPasswordRestart;
	private Date codeForPasswordRestartCreated;
	
	private AffiliationUser companyAffiliationUser;
	private Integer companyAffiliationUserId;
	
	public Boolean getIsBeginner() {
		return isBeginner;
	}
	public void setIsBeginner(Boolean isBeginner) {
		this.isBeginner = isBeginner;
	}
	
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
	public String getRecomendedUser() {
		return recomendedUser;
	}
	public void setRecomendedUser(String recomendedUser) {
		this.recomendedUser = recomendedUser;
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
	public Integer getStatisticsPlayedGames() {
		return statisticsPlayedGames;
	}
	public void setStatisticsPlayedGames(Integer statisticsPlayedGames) {
		this.statisticsPlayedGames = statisticsPlayedGames;
	}
	public Integer getStatisticsWonGames() {
		return statisticsWonGames;
	}
	public void setStatisticsWonGames(Integer statisticsWonGames) {
		this.statisticsWonGames = statisticsWonGames;
	}
	public Integer getStatisticsBoughtBoxes() {
		return statisticsBoughtBoxes;
	}
	public void setStatisticsBoughtBoxes(Integer statisticsBoughtBoxes) {
		this.statisticsBoughtBoxes = statisticsBoughtBoxes;
	}
	public Integer getStatisticsUsedCredits() {
		return statisticsUsedCredits;
	}
	public void setStatisticsUsedCredits(Integer statisticsUsedCredits) {
		this.statisticsUsedCredits = statisticsUsedCredits;
	}
	public Boolean getValidated() {
		return validated;
	}
	public void setValidated(Boolean validated) {
		this.validated = validated;
	}
	public String getValidationCode() {
		return validationCode;
	}
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}
	public String getCodeForPasswordRestart() {
		return codeForPasswordRestart;
	}
	public void setCodeForPasswordRestart(String codeForPasswordRestart) {
		this.codeForPasswordRestart = codeForPasswordRestart;
	}
	public Date getCodeForPasswordRestartCreated() {
		return codeForPasswordRestartCreated;
	}
	public void setCodeForPasswordRestartCreated(Date codeForPasswordRestartCreated) {
		this.codeForPasswordRestartCreated = codeForPasswordRestartCreated;
	}
	public Integer getAffiliatedUsers() {
		return affiliatedUsers;
	}
	public void setAffiliatedUsers(Integer affiliatedUsers) {
		this.affiliatedUsers = affiliatedUsers;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getBuyCreditsPromoCode() {
		return buyCreditsPromoCode;
	}
	public void setBuyCreditsPromoCode(String buyCreditsPromoCode) {
		this.buyCreditsPromoCode = buyCreditsPromoCode;
	}
	public String getBuyCreditsPromoCodeText() {
		return buyCreditsPromoCodeText;
	}
	public void setBuyCreditsPromoCodeText(String buyCreditsPromoCodeText) {
		this.buyCreditsPromoCodeText = buyCreditsPromoCodeText;
	}
	public AffiliationUser getCompanyAffiliationUser() {
		return companyAffiliationUser;
	}
	public void setCompanyAffiliationUser(AffiliationUser companyAffiliationUser) {
		this.companyAffiliationUser = companyAffiliationUser;
	}
	public Integer getCompanyAffiliationUserId() {
		return companyAffiliationUserId;
	}
	public void setCompanyAffiliationUserId(Integer companyAffiliationUserId) {
		this.companyAffiliationUserId = companyAffiliationUserId;
	}
	public Boolean getIsFraudulent() {
		return isFraudulent;
	}
	public void setIsFraudulent(Boolean isFraudulent) {
		this.isFraudulent = isFraudulent;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Boolean getIsSubscribed() {
		return isSubscribed;
	}
	public void setIsSubscribed(Boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
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

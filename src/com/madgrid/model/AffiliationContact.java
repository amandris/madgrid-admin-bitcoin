package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class AffiliationContact implements Serializable{

	private Integer id;
	private Date created;
	private AffiliationUser affiliationUser;
	private Integer affiliationUserId;
	private String subject;
	private String message;
	private boolean isRead;
	
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
	public AffiliationUser getAffiliationUser() {
		return affiliationUser;
	}
	public void setAffiliationUser(AffiliationUser affiliationUser) {
		this.affiliationUser = affiliationUser;
	}
	public Integer getAffiliationUserId() {
		return affiliationUserId;
	}
	public void setAffiliationUserId(Integer affiliationUserId) {
		this.affiliationUserId = affiliationUserId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(boolean isRead) {
		this.isRead = isRead;
	}
	
	
}

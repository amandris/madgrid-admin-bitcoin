package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Tweet implements Serializable{

	private Integer id;
	private Date created;
	private User user;
	private Win win;
	private Integer userId;
	private Integer winId;
	private Boolean paid;
	private String text;
	
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Win getWin() {
		return win;
	}
	public void setWin(Win win) {
		this.win = win;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getWinId() {
		return winId;
	}
	public void setWinId(Integer winId) {
		this.winId = winId;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
}

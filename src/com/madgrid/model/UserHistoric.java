package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class UserHistoric implements Serializable{

	public static final int BUY_CREDITS = 1;
	public static final int GET_CREDITS_BY_PROMO = 2;
	public static final int BUY_BOX = 3;
	public static final int PARTIAL_WIN = 4;
	public static final int FINAL_WIN = 5;
	public static final int GET_CREDIT_BY_VALIDATION = 6;
	public static final int GET_CREDIT_BY_AFFILIATE = 7;
	public static final int GET_CREDIT_BY_REFUND = 8;
	public static final int GET_CREDIT_BY_ADMIN_MORE = 9;
	public static final int GET_CREDIT_BY_ADMIN_LESS = 10;
	public static final int GET_CREDIT_IN_MULTIPRIZE = 11;
	public static final int GET_CREDIT_FOR_TWEET = 12;
	
	private Integer id;
	private Date created;
	private User user;
	private Integer userId;
	private Integer type;
	private Double value1;
	private String value2;
	private String value3;
	
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getValue1() {
		return value1;
	}
	public void setValue1(Double value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
	
	
}

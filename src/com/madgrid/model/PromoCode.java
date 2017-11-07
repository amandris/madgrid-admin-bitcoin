package com.madgrid.model;

import java.io.Serializable;

public class PromoCode implements Serializable{
	
	public static final int PROMOCODE_TYPE_5_IN_REGISTER = 1;
	public static final int PROMOCODE_TYPE_10_IN_REGISTER = 2;
	public static final int PROMOCODE_TYPE_15_IN_REGISTER = 3;
	public static final int PROMOCODE_TYPE_20_IN_REGISTER = 4;
	
	public static final int PROMOCODE_TYPE_2x1_IN_BUY = 5;
	public static final int PROMOCODE_TYPE_50_PERCENT_IN_BUY = 6;
	public static final int PROMOCODE_TYPE_20_PERCENT_IN_BUY = 7;
	public static final int PROMOCODE_TYPE_10_PERCENT_IN_BUY = 8;
	public static final int PROMOCODE_TYPE_10_IN_BUY = 9;
	
	
	private Integer id;
	private Integer type;
	private String code;
	private Integer count;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}

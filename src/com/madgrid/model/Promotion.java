package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class Promotion implements Serializable{
	
	public static final int PROMOTION_TYPE_2x1 = 1;
	public static final int PROMOTION_TYPE_10_IN_BUY = 4;
	public static final int PROMOTION_TYPE_5_IN_BUY = 13;
	public static final int PROMOTION_TYPE_1_IN_BUY = 14;
	public static final int PROMOTION_TYPE_10_PERCENT_IN_BUY = 10;
	public static final int PROMOTION_TYPE_20_PERCENT_IN_BUY = 11;
	public static final int PROMOTION_TYPE_30_PERCENT_IN_BUY = 12;
	
	public static final int PROMOTION_TYPE_1_IN_REGISTER = 8;
	public static final int PROMOTION_TYPE_2_IN_REGISTER = 7;
	public static final int PROMOTION_TYPE_5_IN_REGISTER = 6;
	public static final int PROMOTION_TYPE_10_IN_REGISTER = 2;
	public static final int PROMOTION_TYPE_20_IN_REGISTER = 3;
	public static final int PROMOTION_TYPE_RETURN_IF_FAIL = 5;
	
	private Integer id;
	private Date created;
	private Date modified;
	private Date startDate;
	private Date endDate;
	private String name;
	private String description;
	private Integer type;
	
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}

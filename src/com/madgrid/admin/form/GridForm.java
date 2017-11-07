package com.madgrid.admin.form;

import java.io.Serializable;


import org.apache.struts.validator.ValidatorForm;


public class GridForm extends ValidatorForm implements Serializable{

	private Integer id;
	private String itemId;
	private String startDate;
	private Integer type;
	private Integer boxes;
	private Double boxPrice;
	private Integer partialWinSeconds;
	private String virtualPath;
	
	
	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	public Integer getPartialWinSeconds() {
		return partialWinSeconds;
	}
	public void setPartialWinSeconds(Integer partialWinSeconds) {
		this.partialWinSeconds = partialWinSeconds;
	}

	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Integer getBoxes() {
		return boxes;
	}
	public void setBoxes(Integer boxes) {
		this.boxes = boxes;
	}
	public Double getBoxPrice() {
		return boxPrice;
	}
	public void setBoxPrice(Double boxPrice) {
		this.boxPrice = boxPrice;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}

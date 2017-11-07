package com.madgrid.model;

import java.io.Serializable;

public class Box implements Serializable{

	
	public static final int TYPE_FREE = 0;
	public static final int TYPE_BOUGHT = 1;
	public static final int TYPE_WIN = 2;
	
	private Integer id;
	private Integer gridId;
	private Integer userId;
	private Integer pos;
	private Integer type;
	private Double price;
	private Grid grid;
	private User user;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGridId() {
		return gridId;
	}
	public void setGridId(Integer gridId) {
		this.gridId = gridId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
		
	
}

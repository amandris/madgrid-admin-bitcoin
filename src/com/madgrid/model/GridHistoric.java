package com.madgrid.model;

import java.io.Serializable;
import java.util.Date;

public class GridHistoric implements Serializable{

	public static final int START = 1;
	public static final int BUY_BOX = 2;
	public static final int PARTIAL_WIN = 3;
	public static final int FINAL_WIN = 4;
	public static final int NO_WIN = 5;
	public static final int FINAL_WIN_PREVIOUS_USER = 6;
	public static final int FIND_MULTIPRIZE = 7;
	
	private Integer id;
	private Integer gridId;
	private Date created;
	private Integer type;
	private Double value1;
	private String value2;
	private String value3;
	private Grid grid;
	
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
	
	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
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
	public Integer getGridId() {
		return gridId;
	}
	public void setGridId(Integer gridId) {
		this.gridId = gridId;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	
		
}

package com.madgrid.admin.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class PromoCodeForm extends ValidatorForm implements Serializable{

	private Integer id;
	private String code;
	private Integer type;
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

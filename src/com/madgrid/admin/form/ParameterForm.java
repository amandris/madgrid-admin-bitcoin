package com.madgrid.admin.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class ParameterForm extends ValidatorForm implements Serializable{

	private Integer id;
	private String name;
	private String value;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

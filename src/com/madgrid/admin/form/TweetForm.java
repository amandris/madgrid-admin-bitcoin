package com.madgrid.admin.form;

import java.io.Serializable;


import org.apache.struts.validator.ValidatorForm;


public class TweetForm extends ValidatorForm implements Serializable{

	private Integer id;
	private Boolean paid;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Boolean getPaid() {
		return paid;
	}
	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
		
		
}

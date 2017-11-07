package com.madgrid.admin.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class AnswerForm extends ValidatorForm implements Serializable{

	private Integer id;

	private String message;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}

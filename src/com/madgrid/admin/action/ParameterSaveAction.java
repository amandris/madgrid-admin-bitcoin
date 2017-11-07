package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.ParameterForm;

import com.madgrid.dao.ParameterDAO;
import com.madgrid.model.Parameter;

public class ParameterSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ParameterDAO parameterDAO = new ParameterDAO();
		Parameter parameter = null;
		ParameterForm parameterForm = (ParameterForm) form;
		
		if( parameterForm.getId() == 0){
			parameter = new Parameter();
			parameter.setId( null);
		} else{
			parameter = parameterDAO.getParameterById( parameterForm.getId());
		}
		
		parameter.setName( parameterForm.getName());
		parameter.setValue(parameterForm.getValue());
		
		parameterDAO.setParameter(parameter);
		return mapping.findForward( "ok");
	}
}		

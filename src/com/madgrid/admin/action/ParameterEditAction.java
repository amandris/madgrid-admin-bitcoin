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


public class ParameterEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ParameterDAO parameterDAO = new ParameterDAO();
		Parameter parameter = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			parameter = parameterDAO.getParameterById( Integer.parseInt( id));
			ParameterForm parameterForm = new ParameterForm();
			
			parameterForm.setId( parameter.getId());
			parameterForm.setName( parameter.getName());
			parameterForm.setValue( parameter.getValue());

			request.setAttribute( "parameterForm", parameterForm);
		}		
		return mapping.findForward( "ok");
	}

}		

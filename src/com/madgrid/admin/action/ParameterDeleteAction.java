package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.ParameterDAO;
import com.madgrid.model.Parameter;

public class ParameterDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		ParameterDAO parameterDAO = new ParameterDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			Parameter parameter = parameterDAO.getParameterById( Integer.parseInt( id));
			parameterDAO.deleteParameter( parameter);
		}
		
		return mapping.findForward( "ok");
	}

}		

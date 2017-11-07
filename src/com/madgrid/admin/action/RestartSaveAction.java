package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.ParameterDAO;
import com.madgrid.model.Parameter;

public class RestartSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		ParameterDAO parameterDAO = new ParameterDAO();
		Criteria criteria = new Criteria();
		criteria.addEqualTo("name", "serverRestart");
		
		Parameter parameter = parameterDAO.getParameterByCriteria( criteria);
		
		if( parameter == null ){
			parameter = new Parameter();
			parameter.setName("serverRestart");
		}
		
		parameter.setValue("true");
		
		parameterDAO.setParameter(parameter);
		
		return mapping.findForward( "ok");
	}

}		

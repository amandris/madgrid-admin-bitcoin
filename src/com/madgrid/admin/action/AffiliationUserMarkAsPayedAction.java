package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.model.AffiliationActivity;

public class AffiliationUserMarkAsPayedAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		
		String affiliationActivityIdString = request.getParameter("id");
		
		int affiliationActivityId = Integer.parseInt( affiliationActivityIdString);
		
		AffiliationActivity affiliationActivity = affiliationActivityDAO.getAffiliationActivityById( affiliationActivityId);
		
		if( affiliationActivity != null){
		
			affiliationActivity.setAlreadyPayed( true);
			affiliationActivityDAO.setAffiliationActivity(affiliationActivity);
			response.getWriter().println( "ok");
			
			return null;
			
		}
		
		response.getWriter().println( "error");
		
		return null; 
	}

}		

package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AdminUserDAO;
import com.madgrid.model.AdminUser;

public class AdminUserDeleteAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AdminUserDAO adminUserDAO = new AdminUserDAO();

		String id=request.getParameter( "id");
		
		if( id != null){
			AdminUser adminUser = adminUserDAO.getAdminUserById( Integer.parseInt( id));
			adminUserDAO.deleteAdminUser( adminUser);
		}
		
		return mapping.findForward( "ok");
	}

}		

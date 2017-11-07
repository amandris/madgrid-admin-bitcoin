package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AdminUserForm;
import com.madgrid.dao.AdminUserDAO;
import com.madgrid.model.AdminUser;


public class AdminUserEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AdminUserDAO adminUserDAO = new AdminUserDAO();
		AdminUser adminUser = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			adminUser = adminUserDAO.getAdminUserById( Integer.parseInt( id));
			
			AdminUserForm adminUserForm = new AdminUserForm();
			BeanUtils.copyProperties(adminUserForm, adminUser);
			
			adminUserForm.setPassword( null);
			request.setAttribute( "adminUserForm", adminUserForm);
		}		
		return mapping.findForward( "ok");
	}

}		

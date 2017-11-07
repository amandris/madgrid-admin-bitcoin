package com.madgrid.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.AdminUserForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.AdminUserDAO;
import com.madgrid.model.AdminUser;

public class AdminUserSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AdminUserDAO adminUserDAO = new AdminUserDAO();
		AdminUser adminUser = null;
		AdminUserForm adminUserForm = (AdminUserForm) form;
		ActionErrors 				errors						= new ActionErrors();
		
		if( adminUserForm.getId() == 0){
			if( Utils.nullOrBlank( adminUserForm.getPassword())){
				errors.add( "password",new ActionError( "errors.required"));
				saveErrors	( request, errors);
				return mapping.getInputForward();
			}
			adminUser = new AdminUser();
			BeanUtils.copyProperties(adminUser, adminUserForm);
			adminUser.setId( null);
			adminUser.setCreated( Utils.today());
			adminUser.setModified( Utils.today());
			adminUser.setPassword( Utils.digest(adminUserForm.getPassword()));
		} else{
			adminUser = adminUserDAO.getAdminUserById( adminUserForm.getId());
			Date createdDate = adminUser.getCreated();
			String password = adminUser.getPassword();
			BeanUtils.copyProperties(adminUser, adminUserForm);
			adminUser.setCreated( createdDate);
			adminUser.setModified( Utils.today());
			if( Utils.nullOrBlank( adminUserForm.getPassword())){
				adminUser.setPassword( password);
			} else {
				adminUser.setPassword( Utils.digest(adminUserForm.getPassword()));
			}
			
		}
		
		adminUserDAO.setAdminUser(adminUser);
		
		return mapping.findForward( "ok");
	}

}		

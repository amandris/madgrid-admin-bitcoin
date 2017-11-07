package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.LoginForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.AdminUserDAO;
import com.madgrid.model.AdminUser;

public class AdminLoginAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		AdminUserDAO adminUserDAO = new AdminUserDAO();
		LoginForm loginForm = (LoginForm) form;
		ActionErrors errors	= new ActionErrors();
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "login", loginForm.getLogin());
		criteria.addEqualTo( "password", Utils.digest(loginForm.getPassword()));
		
		AdminUser adminUser= adminUserDAO.getAdminUserByCriteria(criteria);

		if( adminUser == null){
			errors.add( "login",new ActionError( "errors.login"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		request.getSession().setAttribute( "adminUserSession", adminUser);
		
		return mapping.findForward( "ok");
	}

}		

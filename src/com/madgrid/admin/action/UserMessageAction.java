package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserMessageForm;
import com.madgrid.dao.UserDAO;
import com.madgrid.model.User;


public class UserMessageAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		UserDAO userDAO = new UserDAO();
		User user = null; 
		String id = (String)request.getParameter("id");

		if( id !=  null){
			user = userDAO.getUserById( Integer.parseInt( id));
			
			UserMessageForm userMessageForm = new UserMessageForm();
			
			userMessageForm.setId( user.getId());
			request.setAttribute( "userMessageForm", userMessageForm);
			request.setAttribute( "user", user);
		}		
		return mapping.findForward( "ok");
	}

}		

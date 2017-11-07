package com.madgrid.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserMessageForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationContact;
import com.madgrid.model.AffiliationUser;



public class AffiliationUserMessageAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationuserDAO = new AffiliationUserDAO();
		AffiliationUser affiliationUser = null; 
		String id = (String)request.getParameter("id");
		String messageId = (String)request.getParameter("messageId");

		if( !Utils.nullOrBlank(id) && Utils.nullOrBlank(messageId)){
			affiliationUser = affiliationuserDAO.getAffiliationUserById( Integer.parseInt( id));
			
			UserMessageForm userMessageForm = new UserMessageForm();
			
			userMessageForm.setId( affiliationUser.getId());
			request.setAttribute( "userMessageForm", userMessageForm);
			request.setAttribute( "affiliationUser", affiliationUser);
		} else if( Utils.nullOrBlank(id) && !Utils.nullOrBlank(messageId)){
			AffiliationContactDAO affiliationContactDAO = new AffiliationContactDAO();
			AffiliationContact affiliationContact = affiliationContactDAO.getAffiliationContactById(Integer.parseInt(messageId));
			if( affiliationContact != null){
				affiliationUser = affiliationuserDAO.getAffiliationUserById( affiliationContact.getAffiliationUserId());
				
				UserMessageForm userMessageForm = new UserMessageForm();
				
				userMessageForm.setId( affiliationUser.getId());
				if( !Utils.nullOrBlank(affiliationContact.getSubject())){
					userMessageForm.setSubject( "Re: " + affiliationContact.getSubject());
				}
				request.setAttribute( "userMessageForm", userMessageForm);
				request.setAttribute( "affiliationUser", affiliationUser);
			}
		}
		return mapping.findForward( "ok");
	}

}		

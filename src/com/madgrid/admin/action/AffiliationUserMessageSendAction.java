package com.madgrid.admin.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.admin.form.UserMessageForm;
import com.madgrid.admin.util.Utils;
import com.madgrid.admin.util.mail.AffiliationUserMessageMailObject;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;
import com.madgrid.admin.util.Mail;

public class AffiliationUserMessageSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "adminUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
		AffiliationUser affiliationUser = null;
		UserMessageForm userMessageForm = (UserMessageForm) form;
		ActionErrors errors	= new ActionErrors();
		
		
		if( Utils.nullOrBlank( userMessageForm.getSubject())){
			errors.add( "subject",new ActionError( "errors.required"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		if( Utils.nullOrBlank( userMessageForm.getMessage())){
			errors.add( "message",new ActionError( "errors.required"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
	
		
		affiliationUser = affiliationUserDAO.getAffiliationUserById( userMessageForm.getId());
		
		AffiliationUserMessage affiliationUserMessage = new AffiliationUserMessage();
		affiliationUserMessage.setCreated( Utils.today());
		affiliationUserMessage.setSubject( userMessageForm.getSubject());
		affiliationUserMessage.setMessage( userMessageForm.getMessage());
		affiliationUserMessage.setAffiliationUser(affiliationUser);
		affiliationUserMessage.setAffiliationUserId(affiliationUser.getId());
		
		affiliationUserMessageDAO.setAffiliationUserMessage(affiliationUserMessage);
		
		
		AffiliationUserMessageMailObject affiliationUserMessageMailObject = new AffiliationUserMessageMailObject( userMessageForm.getMessage(), Utils.getBaseUrl());
		Mail mail = new Mail( affiliationUser.getEmail(), userMessageForm.getSubject(),  affiliationUserMessageMailObject);
		mail.start();
		
		return mapping.findForward( "ok");
	}

}		
